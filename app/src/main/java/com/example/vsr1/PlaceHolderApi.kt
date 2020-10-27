package com.example.vsr

import com.example.vsr1.Joke
import com.example.vsr1.Value
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderApi {

    @GET("random")
    fun getPost(): Call<Joke>

}