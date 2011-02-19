package com.radiadesign.relationalscope.comparison

import com.radiadesign.relationalscope.RelationalScope

class ComparisonFactory {
  
  private ComparisonFactory() { }
  
  static equals(lhs, rhs) {
    new EqualsComparison(lhs, rhs)
  }
  
  static eq(lhs, rhs) {
    equals(lhs, rhs)
  }
  
  static notEquals(lhs, rhs) {
    new NotEqualsComparison(lhs, rhs)
  }
  
  static ne(lhs, rhs) {
    notEquals(lhs, rhs)
  }
  
  static is(lhs, rhs) {
    new IsComparison(lhs, rhs)
  }
  
  static lt(lhs, rhs) {
    new LessThanComparison(lhs, rhs)
  }
  
  static lte(lhs, rhs) {
    new LessThanOrEqualsComparison(lhs, rhs)
  }
  
  static le(lhs, rhs) {
    lte(lhs, rhs)
  }
  
  static gt(lhs, rhs) {
    new GreaterThanComparison(lhs, rhs)
  }
  
  static gte(lhs, rhs) {
    new GreaterThanOrEqualsComparison(lhs, rhs)
  }
  
  static ge(lhs, rhs) {
    gte(lhs, rhs)
  }
  
  static 'in'(lhs, rhs) {
    new InComparison(lhs, rhs)
  }
  
  static includes(lhs, rhs) {
    new IncludesComparison(lhs, rhs)
  }
  
  static include(lhs, rhs) {
    includes(lhs, rhs)
  }
  
  static contains(lhs, rhs) {
    includes(lhs, rhs)
  }
  
  static contain(lhs, rhs) {
    includes(lhs, rhs)
  }
  
  static like(lhs, rhs) {
    new LikeComparison(lhs, rhs)
  }
  
  static ilike(lhs, rhs) {
    new ILikeComparison(lhs, rhs)
  }
  
  static between(lhs, rhs) {
    new BetweenComparison(lhs, rhs)
  }
  
  static betwixt(lhs, rhs) {
    between(lhs, rhs)
  }
  
  static exists(RelationalScope scope) {
    new ExistsComparison(scope)
  }
  
  static mapTo(property, value) {
    new PropertyMappingComparison(property, value)
  }
  
}