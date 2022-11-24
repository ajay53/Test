package com.example.test.repo

import com.example.test.Todos
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos/{id}")
    suspend fun getTodos(@Path("id") id: Int): Todos

    @GET("todos/{id}")
    suspend fun getTodosBody(@Path("id") id: Int): Response<Todos>
}