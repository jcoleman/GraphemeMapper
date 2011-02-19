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
    adapt(this.scope)
  }
  
  
  // --------------------------------------------------------------------------
  // RelationScope Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def beforeAdaptingScopeChild(int index, scopeOrComparison) {
    
  }
  
  def adaptEachScopeChild(RelationalScope currentScope) {
    def scopes = currentScope.scopes
    int len = scopes.size()
    for (int i = 0; i < len; ++i) {
      def scopeOrComparison = scopes[i]
      beforeAdaptingScopeChild(i, scopeOrComparison)
      adapt(scopeOrComparison)
    }
  }
  
  def adapt(RelationalScope currentScope) {
    adaptEachScopeChild(currentScope)
  }
  
  def adapt(OrRelationalScope currentScope) {
    adaptEachScopeChild(currentScope)
  }
  
  def adapt(NotRelationalScope currentScope) {
    adaptEachScopeChild(currentScope)
  }
  
  
  // --------------------------------------------------------------------------
  // Comparison Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def betweenComparisonValues(ComparisonBase currentComparison, lhsValue, rhsValue) {
    
  }
  
  def adapt(ComparisonBase currentComparison) {
    adapt(currentComparison.lhsValue)
    betweenComparisonValues(currentComparison, currentComparison.lhsValue, currentComparison.rhsValue)
    adapt(currentComparison.rhsValue)
  }
  
  def adapt(BetweenComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(EqualsComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(ExistsComparison currentComparison) {
    adapt(currentComparison.scope)
  }
  
  def adapt(GreaterThanComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(GreaterThanOrEqualsComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(ILikeComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(IncludesComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(InComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(IsComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(LessThanComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(LessThanOrEqualsComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(LikeComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(NotEqualsComparison currentComparison) {
    adapt((ComparisonBase) currentComparison)
  }
  
  def adapt(PropertyMappingComparison currentComparison) {
    // TODO: Implement quasi-comparison logic here
    //       -- this is state-mutating adding a mapping to the stack
  }
  
  
  // --------------------------------------------------------------------------
  // Expression Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def adapt(ExpressionBase currentExpression) {
    
  }
  
  def adapt(AbstractPropertyExpression currentExpression) {
    
  }
  
  def adapt(LocalPropertyExpression currentExpression) {
    
  }
  
  def adapt(MappedPropertyExpression currentExpression) {
    
  }
  
  def adapt(ValueExpression currentExpression) {
    
  }
  
  
  // --------------------------------------------------------------------------
  // Selection Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def adapt(AbstractSelection currentSelection) {
    
  }
  
  def adapt(AverageSelection currentSelection) {
    
  }
  
  def adapt(CountSelection currentSelection) {
    
  }
  
  def adapt(DistinctSelection currentSelection) {
    
  }
  
  def adapt(MaximumSelection currentSelection) {
    
  }
  
  def adapt(MinimumSelection currentSelection) {
    
  }
  
  def adapt(PropertySelection currentSelection) {
    
  }
  
  def adapt(SummationSelection currentSelection) {
    
  }
  
  
}