package com.example.homework12_1.data


import com.google.gson.annotations.SerializedName

data class JsonClassItem(
    @SerializedName("Description")
    val description: String,
    @SerializedName("Path")
    val path: String
)