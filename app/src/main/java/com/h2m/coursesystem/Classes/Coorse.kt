package com.h2m.coursesystem.Classes

class StudentCorse {
    var id:Int?=null
    var name:String?=null
    var corse:String?=null
    var fee: String?= null

    override fun toString(): String {
        val S :String ="$id) $name) $corse) $fee)"
        return S
    }
}