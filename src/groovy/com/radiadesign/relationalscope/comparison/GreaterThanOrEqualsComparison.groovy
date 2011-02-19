package com.radiadesign.relationalscope.comparison

class GreaterThanOrEqualsComparison extends ComparisonBase {
  
  GreaterThanOrEqualsComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} >= ${rhsValue})"
  }
  
}