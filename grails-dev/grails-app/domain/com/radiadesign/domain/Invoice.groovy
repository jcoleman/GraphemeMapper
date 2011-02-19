package com.radiadesign.domain

import com.radiadesign.relationalscope.RelationalScope

class Invoice {
  Long id
  
  User user // belongs-to (w/cascade)
  Long userId
  // BigDecimal total // calculated on each select???
  ArrayList lineItems // has-many
  
  static _persistedFields_ = [
    id: [ klass: Long, column: "id" ]
  ]
  static _persistedAssociations_ = [
    user: [ klass: User, field: "userId", column: "user_id", type: "BELONGS_TO" ],
    lineItems: [ klass: LineItem, referencingAssociation: "invoice", type: "HAS_MANY" ]
  ]
  
  static blankScope() {
    new RelationalScope(Invoice)
  }
  
  static defaultScope() {
    blankScope()
  }
  
}