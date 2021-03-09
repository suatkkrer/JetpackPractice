package com.suatkkrer.jetpackpractice.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.suatkkrer.jetpackpractice.Model.Jokes
import com.suatkkrer.jetpackpractice.R
import com.suatkkrer.jetpackpractice.Service.JokeAPIService
import com.suatkkrer.jetpackpractice.ViewModel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.StringBuilder


class MainFragment : Fragment() {

    private var jokeString = ""
    private lateinit var viewModel: MainViewModel

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

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        firstFragmentButton.setOnClickListener {

            viewModel.getDataFromAPI(jokeSelect())


        }

        firstFragmentButton2.setOnClickListener {
            observeLiveData()
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

    fun jokeSelect() : String  {

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

        return jokeString

    }

    fun observeLiveData() {
        viewModel.jokeList.observe(viewLifecycleOwner, Observer { jokes ->
            jokes?.let {
                Log.e("ASSSSSSSSSSSSSSSSSSSS", it[0].jokeOnly.toString())
            }
        })
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