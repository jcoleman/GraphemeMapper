package com.radiadesign.graphememapper.sql

import com.radiadesign.relationalscope.DepthFirstAdapter
import com.radiadesign.relationalscope.RelationalScope
import com.radiadesign.relationalscope.OrRelationalScope
import com.radiadesign.relationalscope.NotRelationalScope
import com.radiadesign.relationalscope.comparison.*
import com.radiadesign.relationalscope.expression.*
import com.radiadesign.relationalscope.selection.*


class SqlAdapter extends DepthFirstAdapter {
  
  static int INDENTATION_SIZE = 4
  
  // --------------------------------------------------------------------------
  // Public API
  // --------------------------------------------------------------------------
  
  SqlAdapter(RelationalScope _scope) {
    super(_scope)
  }
  
  
  // --------------------------------------------------------------------------
  // Private API
  // --------------------------------------------------------------------------
  
  def sqlWriter = new StringWriter()
  
  def emit(String _sql) {
    sqlWriter.write(_sql)
  }
  
  def emitIndentation() {
    emit(' ' * (currentIndentationCount * INDENTATION_SIZE))
  }
  
  def emitValue(value) {
    if (value instanceof Number) {
      emit(value.toString())
    } else if (value instanceof String) {
      emit('\'')
      emit(value)
      emit('\'')
    } else if (value instanceof GString) {
      emit('\'')
      emit(value.toString())
      emit('\'')
    } else if (value instanceof List) {
      emit('(')
      int len = value.size()
      for (int i = 0; i < len; ++i) {
        emitValue(value[i])
        if (i < (len-1)) {
          emit(', ')
        }
      }
      emit(')')
    } else if (value.equals(null)) {
      emit('NULL')
    } else {
      // TODO: Other cases?
    }
  }
  
  
  // --------------------------------------------------------------------------
  // RelationScope Class Adapter Methods
  // --------------------------------------------------------------------------
  
  String currentConjunction
  int currentIndentationCount
  
  def beforeAdaptingScopeChild(int index, scopeOrComparison, parent) {
    emitIndentation()
    
    if (index != 0) {
      // Only emit the conjunction separator if we are between children
      emit(this.currentConjunction)
    }
  }
  
  def afterAdaptingScopeChild(int index, scopeOrComparison, parent) {
    emit('\n')
  }
  
  def adapt(RelationalScope currentScope, parent) {
    if (parent != null) {
      emit('(\n')
      ++this.currentIndentationCount
    }
    
    emitIndentation()
    emit('SELECT ...\n')
    emitIndentation()
    emit('FROM ...\n')
    emitIndentation()
    emit('WHERE\n')
    
    emitIndentation()
    emit('(\n')
    ++this.currentIndentationCount
    
    this.currentConjunction = 'AND '
    super.adapt((RelationalScope) currentScope, parent)
    
    --this.currentIndentationCount
    emitIndentation()
    emit(')\n')
    
    if (parent != null) {
      --this.currentIndentationCount
      emitIndentation()
      emit(')\n')
    }
  }
  
  def adapt(OrRelationalScope currentScope, parent) {
    this.currentConjunction = 'OR '
    super.adapt(currentScope, parent)
  }
  
  def adapt(NotRelationalScope currentScope, parent) {
    emit('NOT (\n')
    ++this.currentIndentationCount
    
    this.currentConjunction = 'OR '
    super.adapt(currentScope, parent)
    
    --this.currentIndentationCount
    emitIndentation()
    emit(')')
  }
  
  
  // --------------------------------------------------------------------------
  // Comparison Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def adapt(BetweenComparison currentComparison, parent) {
    emit('BETWEEN ')
    
    super.adapt(currentComparison, parent)
  }
  
  def betweenComparisonValues(BetweenComparison currentComparison, lhsValue, rhsValue) {
    emit(' AND ')
  }
  
  def betweenComparisonValues(EqualsComparison currentComparison, lhsValue, rhsValue) {
    emit(' = ')
  }
  
  def adapt(ExistsComparison currentComparison, parent) {
    emit('EXISTS ')
    adapt(scope, currentComparison)
  }
  
  def betweenComparisonValues(GreaterThanComparison currentComparison, lhsValue, rhsValue) {
    emit(' > ')
  }
  
  def betweenComparisonValues(GreaterThanOrEqualsComparison currentComparison, lhsValue, rhsValue) {
    emit(' >= ')
  }
  
  def betweenComparisonValues(ILikeComparison currentComparison, lhsValue, rhsValue) {
    emit(' ILIKE ')
  }
  
  def betweenComparisonValues(IncludesComparison currentComparison, lhsValue, rhsValue) {
    
  }
  
  def betweenComparisonValues(InComparison currentComparison, lhsValue, rhsValue) {
    emit(' IN ')
  }
  
  def betweenComparisonValues(IsComparison currentComparison, lhsValue, rhsValue) {
    
  }
  
  def betweenComparisonValues(LessThanComparison currentComparison, lhsValue, rhsValue) {
    emit(' < ')
  }
  
  def betweenComparisonValues(LessThanOrEqualsComparison currentComparison, lhsValue, rhsValue) {
    emit(' <= ')
  }
  
  def betweenComparisonValues(LikeComparison currentComparison, lhsValue, rhsValue) {
    emit(' LIKE ')
  }
  
  def betweenComparisonValues(NotEqualsComparison currentComparison, lhsValue, rhsValue) {
    emit(' != ')
  }
  
  def betweenComparisonValues(PropertyMappingComparison currentComparison, lhsValue, rhsValue) {
    // TODO: Implement quasi-comparison logic here
    //       -- this is state-mutating adding a mapping to the stack
  }
  
  
  // --------------------------------------------------------------------------
  // Expression Class Adapter Methods
  // --------------------------------------------------------------------------
  
  def adapt(ExpressionBase currentExpression, parent) {
    
  }
  
  def adapt(AbstractPropertyExpression currentExpression, parent) {
    emit(currentExpression.propertyKey)
  }
  
  def adapt(LocalPropertyExpression currentExpression, parent) {
    emit(currentExpression.propertyKey)
  }
  
  def adapt(MappedPropertyExpression currentExpression, parent) {
    emit(currentExpression.propertyKey)
  }
  
  def adapt(ValueExpression currentExpression, parent) {
    emitValue(currentExpression.value)
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