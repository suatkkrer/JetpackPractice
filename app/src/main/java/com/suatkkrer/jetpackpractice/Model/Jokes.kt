package com.suatkkrer.jetpackpractice.Model

import com.google.gson.annotations.SerializedName

data class Jokes(

     @SerializedName("category")
     val categoryJokes : String?,
//     @SerializedName("setup")
//     val firstJoke : String?,
//     @SerializedName("delivery")
//     val secondJoke : String?,
     @SerializedName("joke")
     val jokeOnly : String?,
     @SerializedName("error")
     val errorJoke : String?,
     @SerializedName("type")
     val typeJoke : String?,
     @SerializedName("flags")
     val flagsJoke : String?,
     @SerializedName("id")
     val idJoke : String?,
     @SerializedName("safe")
     val safeJoke : String?,
     @SerializedName("lang")
     val langJoke : String?
 )

