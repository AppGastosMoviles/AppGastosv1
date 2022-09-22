package com.sebastianb.myapplication.model

data class Gasto(
    var id:           String?=null,
    var date:         String?=null,
    var category:     String?=null,
    var description:  String?=null,
    var amount:       Float?=null,
    var establishment:String?=null
)
