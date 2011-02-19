package com.radiadesign.relationalscope.comparison

import com.radiadesign.relationalscope.RelationalScope
import com.radiadesign.relationalscope.expression.*

class ComparisonBase {
  
  def lhsValue
  def rhsValue
  
  
  // --------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------
  
  ComparisonBase() {
    
  }
  
  ComparisonBase(_lhsValue, _rhsValue) {
    lhsValue = _lhsValue
    rhsValue = _rhsValue
  }
  
  
  // --------------------------------------------------------------------------
  // Public API
  // --------------------------------------------------------------------------
  
  String toString() {
    return "ScopeComparisonBase[rhsValue: ${rhsValue}, lhsValue: ${lhsValue}]"
  }
  
}