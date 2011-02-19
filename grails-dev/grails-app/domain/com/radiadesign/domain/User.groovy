package com.radiadesign.domain

import com.radiadesign.relationalscope.RelationalScope

class User {
  Long id
  
  String username
  ArrayList friends // has-many
  Friend bestFriend // belongs-to
  Long bestFriendId
  ArrayList invoices // has-many
  
  static _persistedFields_ = [
    id: [ klass: Long, column: "id" ],
    username: [ klass: String, column: "username" ]
  ]
  static _persistedAssociations_ = [
    bestFriend: [ klass: Friend, field: "bestFriendId", column: "best_friend_id", type: "BELONGS_TO" ],
    friends: [ klass: Friend, referencingAssociation: "user", type: "HAS_MANY" ],
    invoices: [ klass: Invoice, referencingAssociation: "user", type: "HAS_MANY" ]
  ]
  
  static blankScope() {
    new RelationalScope(User)
  }
  
  static defaultScope() {
    blankScope()
  }
  
}