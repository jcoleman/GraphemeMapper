package com.radiadesign.relationalscope

import com.radiadesign.relationalscope.comparison.*
import com.radiadesign.relationalscope.expression.*
import com.radiadesign.relationalscope.selection.*


class DepthFirstAdapter {
  
  RelationalScope scope
  
  DepthFirstAdapter(RelationalScope _scope) {
    scope = _scope
  }
  
  
  // --------------------------------------------------------------------------
  // Public API
  // --------------------------------------------------------------------------
  
  def execute() {
    adapt(this.scope, null)
  }
  
  
  // --------------------------------------------------------------------------
  // RelationScope Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def beforeAdaptingScopeChild(int index, scopeOrComparison, parent) {
    
  }
  
  def afterAdaptingScopeChild(int index, scopeOrComparison, parent) {
    
  }
  
  def adaptEachScopeChild(RelationalScope currentScope, parent) {
    def scopes = currentScope.scopes
    int len = scopes.size()
    for (int i = 0; i < len; ++i) {
      def scopeOrComparison = scopes[i]
      beforeAdaptingScopeChild(i, scopeOrComparison, currentScope)
      adapt(scopeOrComparison, currentScope)
      afterAdaptingScopeChild(i, scopeOrComparison, currentScope)
    }
  }
  
  def adapt(RelationalScope currentScope, parent) {
    adaptEachScopeChild(currentScope, parent)
  }
  
  def adapt(OrRelationalScope currentScope, parent) {
    adaptEachScopeChild(currentScope, parent)
  }
  
  def adapt(NotRelationalScope currentScope, parent) {
    adaptEachScopeChild(currentScope, parent)
  }
  
  
  // --------------------------------------------------------------------------
  // Comparison Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def betweenComparisonValues(ComparisonBase currentComparison, lhsValue, rhsValue) {
    
  }
  
  def adapt(ComparisonBase currentComparison, parent) {
    adapt(currentComparison.lhsValue, currentComparison)
    betweenComparisonValues(currentComparison, currentComparison.lhsValue, currentComparison.rhsValue)
    adapt(currentComparison.rhsValue, currentComparison)
  }
  
  def adapt(BetweenComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(EqualsComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(ExistsComparison currentComparison, parent) {
    adapt(currentComparison.scope, parent)
  }
  
  def adapt(GreaterThanComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(GreaterThanOrEqualsComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(ILikeComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(IncludesComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(InComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(IsComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(LessThanComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(LessThanOrEqualsComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(LikeComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(NotEqualsComparison currentComparison, parent) {
    adapt((ComparisonBase) currentComparison, parent)
  }
  
  def adapt(PropertyMappingComparison currentComparison, parent) {
    // TODO: Implement quasi-comparison logic here
    //       -- this is state-mutating adding a mapping to the stack
  }
  
  
  // --------------------------------------------------------------------------
  // Expression Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def adapt(ExpressionBase currentExpression, parent) {
    
  }
  
  def adapt(AbstractPropertyExpression currentExpression, parent) {
    
  }
  
  def adapt(LocalPropertyExpression currentExpression, parent) {
    
  }
  
  def adapt(MappedPropertyExpression currentExpression, parent) {
    
  }
  
  def adapt(ValueExpression currentExpression, parent) {
    
  }
  
  
  // --------------------------------------------------------------------------
  // Selection Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def adapt(AbstractSelection currentSelection, parent) {
    
  }
  
  def adapt(AverageSelection currentSelection, parent) {
    
  }
  
  def adapt(CountSelection currentSelection, parent) {
    
  }
  
  def adapt(DistinctSelection currentSelection, parent) {
    
  }
  
  def adapt(MaximumSelection currentSelection, parent) {
    
  }
  
  def adapt(MinimumSelection currentSelection, parent) {
    
  }
  
  def adapt(PropertySelection currentSelection, parent) {
    
  }
  
  def adapt(SummationSelection currentSelection, parent) {
    
  }
  
  
}