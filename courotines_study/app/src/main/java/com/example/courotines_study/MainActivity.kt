package com.example.courotines_study

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.courotines_study.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.getUserData()
        mainActivityViewModel.users.observe(this, Observer { myUsers->
            myUsers.forEach{
                Log.i("MyTag","name is ${it.name}" )
            }
        })

        binding.btnCount.setOnClickListener {
           CoroutineScope(Dispatchers.Main).launch {
               binding.tvCount.text = USerDataManager().getTotalUserCount().toString()
           }
        }
        binding.btnDownloadUserData.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                Log.i("MyTag", "Calculation started...")
                val stock1 = async(IO) { getStock1() }
                val stock2 = async(IO){ getStock2() }
                val total = stock1.await() + stock2.await()
                Log.i("MyTag", "Total is $total")
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private suspend  fun downloadUserData() {
        withContext(Dispatchers.Main){
            for (i in 1..200000) {
                binding.tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
                delay(timeMillis = 100)
            }

        }

    }

    private suspend fun getStock1():Int{
        delay(timeMillis = 10000)
        Log.i("MyTag", "stock 1 returned")
        return 55000
    }

    private suspend fun getStock2():Int{
        delay(timeMillis = 8000)
        Log.i("MyTag", "stock 1 returned")
        return 35000
    }

}