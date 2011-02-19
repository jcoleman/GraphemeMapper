package com.radiadesign.relationalscope.comparison

class PropertyMappingComparison extends ComparisonBase {
  
  PropertyMappingComparison(_mappedValue, _mappingKey) {
    super(_mappedValue, _mappingKey)
  }
  
  String toString() {
    return "(${lhsValue} => ${rhsValue})"
  }
  
}