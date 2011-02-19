package com.radiadesign.relationalscope.comparison

class EqualsComparison extends ComparisonBase {
  
  EqualsComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} == ${rhsValue})"
  }
  
}