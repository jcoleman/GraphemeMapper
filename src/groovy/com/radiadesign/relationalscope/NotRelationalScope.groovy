package com.radiadesign.relationalscope

class NotRelationalScope extends OrRelationalScope {
  
  // --------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------
  
  NotRelationalScope(Class _domain) {
    super(_domain)
  }
  
  protected NotRelationalScope(NotRelationalScope scope) {
    super(scope)
  }
  
  
  // --------------------------------------------------------------------------
  // Public API
  // --------------------------------------------------------------------------

  String toString() {
    "!(${scopes.collect {it.toString()}.join(' || ')})"
  }
  
}