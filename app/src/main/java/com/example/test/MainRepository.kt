package com.example.test

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.test.repo.RetrofitBuilder
import com.example.test.room.DatabaseHandler
import com.example.test.room.TodosDao
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val todosDao: TodosDao) {

    var job: CompletableJob? = null

    fun getTodos(id: Int): LiveData<Todos> {
        job = Job()
        return object : LiveData<Todos>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
//                        val todos: Todos = RetrofitBuilder.fakeApiService.getTodos(id)

                        /*val resCall = RetrofitBuilder.fakeApiService.getTodosCall(id)
                        resCall.enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                Log.d(TAG, "onResponse: ${response.body()?.string()}")
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.d(TAG, "onFailure: ${t.message}")
                            }

                        })*/


                        val resBody = RetrofitBuilder.fakeApiService.getTodosBody(id)
                        resBody.body()
                        resBody.code()
                        resBody.message()
                        /*withContext(Main){
                            value = todos
                            it.complete()
                        }*/
                        postValue(resBody.body())
//                        postValue(todos)
                        it.complete()
                    }
                }
            }
        }
    }

    suspend fun insert(todos: Todos) {
        todosDao.insert(todos)
    }

    fun cancelJobs() {
        job?.cancel()
    }

    companion object {
        private const val TAG = "MainRepository"
    }
}