package com.suatkkrer.jetpackpractice.Service

import com.suatkkrer.jetpackpractice.Model.Jokes
import com.suatkkrer.jetpackpractice.Model.NewJokes
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class JokeAPIService {

    private val BASE_URL = "https://v2.jokeapi.dev/"

    private  val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(JokesAPI::class.java)


    fun getData(jokeString: String) : Single<NewJokes> {
        return api.getJokes(jokeString,"single")
    }


}