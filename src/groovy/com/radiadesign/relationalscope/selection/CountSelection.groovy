package com.radiadesign.relationalscope.selection

class CountSelection extends AbstractSelection {
    
  CountSelection(String _propertyName) {
    super(_propertyName)
  }
  
  CountSelection(DistinctSelection _property) {
    super(_property)
  }
  
}