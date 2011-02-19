package com.radiadesign.relationalscope

class OrRelationalScope extends RelationalScope {
  
  // --------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------
  
  OrRelationalScope(Class _domain) {
    super(_domain)
  }
  
  protected OrRelationalScope(OrRelationalScope scope) {
    super(scope)
  }
  
  
  // --------------------------------------------------------------------------
  // Public API
  // --------------------------------------------------------------------------

  String toString() {
    "(${scopes.collect {it.toString()}.join(' || ')})"
  }
  
  
}