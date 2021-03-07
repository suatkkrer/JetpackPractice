package com.suatkkrer.jetpackpractice.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.suatkkrer.jetpackpractice.Model.Jokes
import com.suatkkrer.jetpackpractice.R
import com.suatkkrer.jetpackpractice.Service.JokeAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.StringBuilder


class MainFragment : Fragment() {

    private var jokeString = ""
    private val jokeAPIService = JokeAPIService()
    private val disposable = CompositeDisposable()
    val jokeList = mutableListOf<Jokes>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstFragmentButton.setOnClickListener {

            getDataFromAPI()

        }

        radioButton2.setOnClickListener {
            checkBox.isEnabled = true
            checkBox2.isEnabled = true
            checkBox3.isEnabled = true
            checkBox4.isEnabled = true
            checkBox5.isEnabled = true
            checkBox6.isEnabled = true
        }

        radioButton.setOnClickListener {
            checkBox.isEnabled = false
            checkBox2.isEnabled = false
            checkBox3.isEnabled = false
            checkBox4.isEnabled = false
            checkBox5.isEnabled = false
            checkBox6.isEnabled = false
        }


    }

    fun getDataFromAPI(){



        var arrayString = ArrayList<String>()

        if (radioButton.isChecked) {
            jokeString = "Any"
        } else if (radioButton2.isChecked) {
            if (checkBox.isChecked) {
                arrayString.add("Programming")
            }
            if (checkBox2.isChecked) {
                arrayString.add("Miscellaneous")
            }
            if (checkBox3.isChecked) {
                arrayString.add("Dark")
            }
            if (checkBox4.isChecked) {
                arrayString.add("Pun")
            }
            if (checkBox5.isChecked) {
                arrayString.add("Spooky")
            }
            if (checkBox6.isChecked) {
                arrayString.add("Christmas")
            }
            jokeString = toCSV(arrayString)
            Log.e("JOKE STRING",jokeString.toString())
        } else {
            Toast.makeText(context,"Please select something",Toast.LENGTH_SHORT).show()
        }

        disposable.add(
                jokeAPIService.getData(jokeString)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Jokes>>() {
                            override fun onSuccess(t: List<Jokes>) {

                                jokeList.addAll(t)

                                println(jokeList.size)

                                Log.e("ASDFASDF","ASDGAasdf")


                            }

                            override fun onError(e: Throwable) {
                                    Log.e("ASDFASFD",e.toString())
                            }

                        })

        )

    }


   fun toCSV(array: ArrayList<String>) : String {

       var result = ""

       if(array.size > 0) {
           val sb = StringBuilder()


           for (s in array) {
               sb.append(s).append(",")
           }
           result = sb.deleteCharAt(sb.length - 1).toString()
       }
       return result
   }




}