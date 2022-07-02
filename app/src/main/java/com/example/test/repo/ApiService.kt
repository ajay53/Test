package com.example.test.repo

import com.example.test.Todos
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos/{id}")
    suspend fun getTodos(@Path("id") id: Int): Todos
}