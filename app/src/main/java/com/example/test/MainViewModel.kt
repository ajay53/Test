package com.example.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _id: MutableLiveData<Int> = MutableLiveData()

    val todos: LiveData<Todos> = Transformations
        .switchMap(_id) {
            MainRepository.getTodos(it)
        }

    fun setId(id: Int) {
        if (id != _id.value) {
            _id.value = id
        }
    }

    fun cancelJobs() {
        MainRepository.cancelJobs()
    }
}