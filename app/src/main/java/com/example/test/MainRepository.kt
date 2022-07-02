package com.example.test

import androidx.lifecycle.LiveData
import com.example.test.repo.RetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MainRepository {

    var job: CompletableJob? = null

    fun getTodos(id: Int): LiveData<Todos> {
        job = Job()
        return object : LiveData<Todos>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
                        val todos: Todos = RetrofitBuilder.fakeApiService.getTodos(id)
                        /*withContext(Main){
                            value = todos
                            it.complete()
                        }*/
                        postValue(todos)
                        it.complete()
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}