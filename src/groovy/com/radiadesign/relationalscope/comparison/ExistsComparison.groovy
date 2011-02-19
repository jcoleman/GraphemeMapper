package com.radiadesign.relationalscope.comparison

import com.radiadesign.relationalscope.RelationalScope

class ExistsComparison extends ComparisonBase {
  
  RelationalScope scope
  
  ExistsComparison(RelationalScope _scope) {
    scope = _scope
  }
  
  String toString() {
    return "(exists (${scope}))"
  }
  
}