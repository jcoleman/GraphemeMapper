package com.radiadesign.domain

import com.radiadesign.relationalscope.RelationalScope

class Friend {
  Long id
  
  String name
  User user // belongs-to (w/o cascade)
  Long userId
  
  static _persistedFields_ = [
    id: [ klass: Long, column: "id" ],
    name: [ klass: String, column: "username" ]
  ]
  static _persistedAssociations_ = [
    user: [ klass: User, field: "userId", column: "user_id", type: "BELONGS_TO" ]
  ]
  
  static blankScope() {
    new RelationalScope(Friend)
  }
  
  static defaultScope() {
    blankScope()
  }
  
}