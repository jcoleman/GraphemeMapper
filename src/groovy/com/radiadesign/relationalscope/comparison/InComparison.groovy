package com.radiadesign.relationalscope.comparison

class InComparison extends ComparisonBase {
  
  InComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} in ${rhsValue})"
  }
  
}