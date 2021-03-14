package com.suatkkrer.jetpackpractice.Service


import com.suatkkrer.jetpackpractice.Model.Jokes
import com.suatkkrer.jetpackpractice.Model.NewJokes
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesAPI {

//    @GET("joke/{jokeString}")
//    fun getJokes(@Path("jokeString") jokeString: String,@Query("type") currentType : String):Single<NewJokes>

    @GET("/joke/{jokeString}")
    fun getJokes(@Path("jokeString") jokeString: String):retrofit2.Call<ArrayList<NewJokes>>

}