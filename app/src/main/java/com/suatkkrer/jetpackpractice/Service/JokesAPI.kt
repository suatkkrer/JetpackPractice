package com.suatkkrer.jetpackpractice.Service

import com.suatkkrer.jetpackpractice.Model.Jokes
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesAPI {

    @GET("/joke/{jokeString}")
    fun getJokes(@Path("jokeString") jokeString: String):Single<List<Jokes>>

}