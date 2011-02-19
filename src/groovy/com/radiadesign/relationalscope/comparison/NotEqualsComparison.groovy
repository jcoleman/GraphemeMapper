package com.radiadesign.relationalscope.comparison

class NotEqualsComparison extends ComparisonBase {
  
  NotEqualsComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} != ${rhsValue})"
  }
  
}