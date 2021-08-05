package com.example.homework12_1


 data class JSON_Data(

     val Description: String,
     val Path: String?


 ) {
    override fun toString(): String {
        return Description.toString()
    }
}

