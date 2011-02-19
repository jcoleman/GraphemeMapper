package com.radiadesign.relationalscope.comparison

class BetweenComparison extends ComparisonBase {
  
  BetweenComparison(_lhsValue, _rhsValue) {
    super(_lhsValue, _rhsValue)
  }
  
  String toString() {
    return "(${lhsValue} between ${rhsValue.join(' and ')})"
  }
  
}