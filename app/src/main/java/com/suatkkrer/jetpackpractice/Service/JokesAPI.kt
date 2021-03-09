package com.suatkkrer.jetpackpractice.Service

import com.suatkkrer.jetpackpractice.Model.Jokes
import com.suatkkrer.jetpackpractice.Model.NewJokes
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesAPI {

    @GET("joke/{jokeString}")
    fun getJokes(@Path("jokeString") jokeString: String,@Query("type") currentType : String):Single<NewJokes>

}