package com.suatkkrer.jetpackpractice.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suatkkrer.jetpackpractice.Model.Jokes
import com.suatkkrer.jetpackpractice.Model.NewJokes
import com.suatkkrer.jetpackpractice.Service.JokeAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val jokeAPIService = JokeAPIService()
    private val disposable = CompositeDisposable()

    val jokeList = MutableLiveData<List<Jokes>>()

    fun getDataFromAPI(jokeString : String){

        disposable.add(
                jokeAPIService.getData(jokeString)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<NewJokes>() {
                            override fun onSuccess(t: NewJokes) {

                                Log.e("ASDFASFD","ASDFFFFFFFF")
                                jokeList.value = t.listJokes

                            }

                            override fun onError(e: Throwable) {
                                Log.e("ASDFASFD",e.toString())
                            }

                        })

        )
    }

}