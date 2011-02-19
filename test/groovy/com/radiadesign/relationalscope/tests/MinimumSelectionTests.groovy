package com.radiadesign.relationalscope.tests

import spock.lang.Specification;

class MinimumSelectionTests extends Specification {
  def "test framework works!"() {
    expect:
    foo / 2 == bar
    
    where:
    foo | bar
    6   | 3
    5   | 2
  }
}
