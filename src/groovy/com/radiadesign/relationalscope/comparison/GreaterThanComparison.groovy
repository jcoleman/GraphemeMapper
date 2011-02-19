package com.radiadesign.relationalscope.comparison

class GreaterThanComparison extends ComparisonBase {
  
  GreaterThanComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} > ${rhsValue})"
  }
  
}