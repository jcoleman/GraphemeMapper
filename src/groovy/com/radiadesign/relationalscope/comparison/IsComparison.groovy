package com.radiadesign.relationalscope.comparison

class IsComparison extends ComparisonBase {
  
  IsComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} == ${rhsValue})"
  }
  
}