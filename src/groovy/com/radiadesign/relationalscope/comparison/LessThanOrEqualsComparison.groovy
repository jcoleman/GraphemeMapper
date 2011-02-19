package com.radiadesign.relationalscope.comparison

class LessThanOrEqualsComparison extends ComparisonBase {
  
  LessThanOrEqualsComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} <= ${rhsValue})"
  }
  
}