package com.radiadesign.relationalscope.comparison

class IncludesComparison extends ComparisonBase {
  
  IncludesComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} contains ${rhsValue})"
  }
  
}