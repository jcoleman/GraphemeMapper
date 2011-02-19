package com.radiadesign.domain

import com.radiadesign.relationalscope.RelationalScope

class LineItem {
  Long id
  
  Invoice invoice // belongs-to
  Long invoiceId
  BigDecimal amount
  
  static _persistedFields_ = [
    id: [ klass: Long, column: "id" ],
    amount: [ klass: BigDecimal, column: "amount" ]
  ]
  static _persistedAssociations_ = [
    invoice: [ klass: Invoice, field: "invoiceId", column: "invoice_id", type: "BELONGS_TO" ]
  ]
  
  static blankScope() {
    new RelationalScope(LineItem)
  }
  
  static defaultScope() {
    blankScope()
  }
  
}