package com.radiadesign.relationalscope.comparison

class LessThanComparison extends ComparisonBase {
  
  LessThanComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} < ${rhsValue})"
  }
  
}