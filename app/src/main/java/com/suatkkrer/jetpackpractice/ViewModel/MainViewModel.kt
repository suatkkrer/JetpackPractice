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
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel : ViewModel() {

    private val jokeAPIService = JokeAPIService()
    private val disposable = CompositeDisposable()

    private var jokeList : List<ArrayList<Jokes>>? = null

    fun getDataFromAPI(jokeString : String){

  //      disposable.add(
//                jokeAPIService.getData(jokeString)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(object : DisposableSingleObserver<NewJokes>() {
//                            override fun onSuccess(t: NewJokes) {
//
//                                t.listJokes = ArrayList<Jokes>()
//                                Log.e("ASDFASFD", "ASDFFFFFFFF")
//                                println(t.listJokes[0].jokeOnly)
//                                jokeList.value = t.listJokes
//
//                            }
//
//                            override fun onError(e: Throwable) {
//                                Log.e("ASDFASFD", e.toString())
//                            }
//
//                        })

     //   )

        jokeAPIService.getData(jokeString).enqueue(object : retrofit2.Callback<ArrayList<NewJokes>> {


            override fun onResponse(call: Call<ArrayList<NewJokes>>, response: Response<ArrayList<NewJokes>>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        var jokes = it.map { newJokes ->
                            newJokes.listJokes
                        }
                        jokeList = jokes
                        Log.e("asDFASDFASDF",jokeList!![0][0].jokeOnly.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<NewJokes>>, t: Throwable) {
                Log.e("ASDFASFD", t.toString())
            }

        })



    }

}