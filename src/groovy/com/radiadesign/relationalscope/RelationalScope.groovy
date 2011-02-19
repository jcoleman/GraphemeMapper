package com.radiadesign.relationalscope

class RelationalScope {
  
  Class domain
  
  String associationName
  def scopes = []
  def selections = []
  def selectionKeys = []
  
  def skipCount
  def takeCount
  def orderBy = []
  
  def result
  def resultIsSet = false
  def results
  def resultsIsSet = false
  def resultCount
  def countIsSet = false
  
  def detachedCriteriaCount = 0
  
  // --------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------
  
  RelationalScope(Class _domain) {
    domain = _domain
  }
  
  //RelationalScope(JSONObject json) {
  //  
  //}
  
  protected RelationalScope(RelationalScope scope) {
    // Provides a deep copy of the stored scopes to ensure thread safety
    scopes = scope.scopes.clone()
    domain = scope.domain
    selections = scope.selections.clone()
    selectionKeys = scope.selectionKeys.clone()
    takeCount = scope.takeCount
    skipCount = scope.skipCount
    orderBy = scope.orderBy.clone()
  }
  
  
  // --------------------------------------------------------------------------
  // Public API
  // --------------------------------------------------------------------------
  
  def where(RelationalScope additionalScope) {
    def newScope = this.clone()
    newScope.addScopeOrComparison(additionalScope)
    return newScope
  }
  
  def where(ArrayList additionalScopes) {
    def builder
    def newScope = this.clone()
    
    additionalScopes.each { additionalScope ->
      if (additionalScope instanceof RelationalScope) {
        newScope.addScopeOrComparison(additionalScope)
      } else if (additionalScope instanceof Closure) {
        if (!builder) { builder = new RelationalScopeBuilder(instance()) }
        
        additionalScope = additionalScope.clone()
        additionalScope.delegate = builder
        additionalScope.resolveStrategy = Closure.DELEGATE_FIRST
        additionalScope.call()
      } else {
        throw new RuntimeException("scope.where(ArrayList) expects each item to be an instance of RelationalScope or Closure. Instead found ${additionalScope?.getClass()}")
      }
    }
    
    if (builder) {
      newScope.addScopeOrComparison(builder.relationalScope)
    }
    
    return newScope
  }
  
  def where(Closure block) {
    def builder = new RelationalScopeBuilder(instance())
    block = block.clone()
    block.delegate = builder
    block.resolveStrategy = Closure.DELEGATE_FIRST
    block.call()
    return this.where(block.relationalScope)
  }
  
  def select(ArrayList _selections, ArrayList _selectionKeys=[]) {
    def newScope = this.clone()
    newScope.selections += _selections
    newScope.selectionKeys += _selectionKeys
    return newScope
  }
  
  def select(Closure block) {
    if (selectionKeys.size() > 0) {
      throw new RuntimeException("Invalid use of scope.select(Closure) after use of scope.select(Map)")
    }
    
    def builder// = new SelectionBuilder()
    block = block.clone()
    block.delegate = builder
    block.resolveStrategy = Closure.DELEGATE_FIRST
    block.call()
    return this.select(builder._selections_)
  }
  
  // param _selections is a map with each entry in one the following formats:
  //   <result-key-name>: "propertyName"
  //   <result-key-name>: "association.propertyName"
  //   <result-key-name>: { selection builder } // e.g. { min("propertyName") }
  def select(Map _selections) {
    if (selectionKeys.size() != selections.size()) {
      throw new RuntimeException("Invalid use of scope.select(Map) after use of scope.select(Closure)")
    }
    
    def _selectionKeys = []
    def builder// = new SelectionBuilder()
    _selections.each { kv ->
      if (kv.value instanceof Closure) {
        def block = kv.value.clone()
        block.delegate = builder
        block.resolveStrategy = Closure.DELEGATE_FIRST
        block.call()
      } else if (kv.value instanceof String || kv.value instanceof GString) {
        builder.property(kv.value)
      } else {
        throw new RuntimeException("Invalid use of scope.select(Map): each key-value pair's value must be a Closure, String, or GString")
      }
      
      _selectionKeys << kv.key
    }
    
    return this.select(builder._selections_, _selectionKeys)
  }
  
  def pluck(String property) {
    // TODO: Complete this mock
    return this
  }

  def order(String property, direction = 'asc') {
    direction = direction.toLowerCase()
    if (!(direction.startsWith('asc') || direction.startsWith('desc'))) {
      throw new RuntimeException("Order by direction '${direction}' is invalid. Use 'asc' or 'desc'.")
    }
    
    def newScope = clone()
    newScope.orderBy << [property: property, direction: direction]
    return newScope
  }
  
  def take(count) {
    def newScope = clone()
    newScope.takeCount = count
    return newScope
  }
  
  def skip(count) {
    def newScope = clone()
    newScope.skipCount = count
    return newScope
  }
  
  def all(forceRefresh=false) {
    if (!resultsIsSet || forceRefresh) { executeQuery(false) }
    return results
  }

  def count(forceRefresh=false) {
    if (resultsIsSet && !forceRefresh) {
      return results.size()
    }

    if (!countIsSet || forceRefresh) {
      executeCount()
    } 
    return resultCount
  }
  
  def first(forceRefresh=false) {
    if (all(forceRefresh).size() > 0) {
      return all().first()
    } else {
      return null
    }
  }
  
  def find(forceRefresh=false) {
    if (!resultIsSet || forceRefresh) { executeQuery(true) }
    return result
  }
  
  String toString() {
    "(${scopes.collect {it.toString()}.join(' && ')})"
  }
  
  
  // --------------------------------------------------------------------------
  // Protected API (can be used by this package, should not be used externally)
  // --------------------------------------------------------------------------
  
  def addScopeOrComparison(additionalScope) {
    if ( this.class == RelationalScope
         && this.class == additionalScope.class
         && this.associationName == additionalScope.associationName ) {
      scopes += additionalScope.scopes
    } else {
      scopes << additionalScope
    }
  }
  
  
  // --------------------------------------------------------------------------
  // Private API
  // --------------------------------------------------------------------------
  
  def executeQuery(forceRefresh=false) {
    
  }
  
  def executeCount(forceRefresh=false) {
    
  }
  
  def mapifySelectionList(fields) {
    def map = [:]
    int len = selectionKeys.size()
    for (int i = 0; i < len; ++i) {
      map[selectionKeys[i]] = fields[i]
    }
    return map
  }
  
  def mapifyResultsIfNecessary(results) {
    if (selectionKeys.isEmpty()) {
      return results
    } else {
      def maps = new ArrayList(results.size())
      if (selectionKeys.size() > 1) {
        for (result in results) {
          maps << mapifySelectionList(result)
        }
      } else {
        for (result in results) {
          maps << mapifySelectionList([result])
        }
      }
      return maps
    }
  }
  
  def mapifyResultIfNecessary(result) {
    if (selectionKeys.isEmpty()) {
      return result
    } else {
      if (selectionKeys.size() > 1) {
        return mapifySelectionList(result)
      } else {
        return mapifySelectionList([result])
      }
    }
  }
  
  def instance() {
    this.class.newInstance(domain)
  }
  
  def fullAssociationPath(associationPath) {
    if (associationName) {
      return associationPath ? "${associationPath}.${associationName}" : associationName
    } else {
      return associationPath
    }
  }
  
  // Provides a thread-safe copy of the current RelationalScope
  RelationalScope clone() {
    return this.class.newInstance(this)
  }
  
}