package com.suatkkrer.jetpackpractice.Service

import com.suatkkrer.jetpackpractice.Model.Jokes
import io.reactivex.Single
import retrofit2.http.GET

interface JokesAPI {

    @GET("/joke")
    fun getJokes():Single<List<Jokes>>

}