package com.radiadesign.relationalscope.comparison

class ILikeComparison extends ComparisonBase {
  
  ILikeComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} ilike ${rhsValue})"
  }
  
}