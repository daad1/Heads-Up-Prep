package com.example.headsupprep

data class UsersItem(    val name: String,
                         val pk: Int,
                         val taboo1: String,
                         val taboo2: String,
                         val taboo3: String,
                         var expandeble:Boolean = false
)
