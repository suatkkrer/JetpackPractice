package com.suatkkrer.jetpackpractice.Model

import com.google.gson.annotations.SerializedName

data class Jokes(

     @SerializedName("category")
     val categoryJokes : String?,
     @SerializedName("setup")
     val firstJoke : String?,
     @SerializedName("delivery")
     val secondJoke : String?,
     @SerializedName("joke")
     val jokeOnly : String?
 )

