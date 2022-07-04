package com.example.test

import androidx.lifecycle.*

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _id: MutableLiveData<Int> = MutableLiveData()

    val todos: LiveData<Todos> = Transformations
        .switchMap(_id) {
            repository.getTodos(it)
        }

    fun setId(id: Int) {
        if (id != _id.value) {
            _id.value = id
        }
    }

    fun cancelJobs() {
        repository.cancelJobs()
    }
}

class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}