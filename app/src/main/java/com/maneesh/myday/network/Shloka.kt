package com.maneesh.myday.network


data class Shloka(
    val _id: String,
    val chapter: Int,
    val verse: Int,
    val slok: String,
    val siva: Siva
)

data class Siva(val author: String, val et: String)