package com.radiadesign.relationalscope.comparison

class LikeComparison extends ComparisonBase {
  
  LikeComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} like ${rhsValue})"
  }
  
}