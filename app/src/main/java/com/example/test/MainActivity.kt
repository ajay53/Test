package com.example.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers.IO
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main), View.OnClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.todos.observe(this, Observer {
            Log.d(TAG, "onCreate: todos: ${it.toString()}")
            txt_data.text = it.toString()
        })

        btn_get_data.setOnClickListener(this)
        viewBinding.custom.setText("T Pose")
        viewBinding.custom.setImageViaID(R.drawable.ic_tpose)

        /*CoroutineScope(IO).launch {
            val text = fakeApiRequest()
        }*/
    }

    private fun callApi() {
        viewModel.setId(Integer.parseInt(edt_id.text.toString()))
    }

    private suspend fun fakeApiRequest() {
        /*val text :String = getResultFromApi()
        Log.d(TAG, "onCreate: getResultFromApi: $text")
        withContext(Main){

        }*/
        val job = CoroutineScope(IO).launch {

        }

        val q: PriorityQueue<String> = PriorityQueue { a, b -> a.length - b.length }
        val arr: Array<Int> = arrayOf(5)
        val list: MutableList<String> = ArrayList()
        list.add("one")
        withContext(IO) {
            val job1 = withTimeoutOrNull(800) {
                getResultFromApi()
            }
            val asyncJob: Deferred<String> = async {
                getResultFromApi()
            }
            Log.d(TAG, "fakeApiRequest: ${asyncJob.await()}")
        }
    }

    private suspend fun getResultFromApi(): String {
        Log.d(TAG, "getResultFromApi: called")
        delay(1000)
        return "abcd"
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            btn_get_data.id -> callApi()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}