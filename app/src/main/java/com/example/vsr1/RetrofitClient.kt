package com.example.vsr

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var retrofit: Retrofit? = null
        get() {
            if (field == null) {
                field = Retrofit.Builder()
                    .baseUrl("https://api.icndb.com/jokes/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return field
        }
    fun instance(): PlaceHolderApi{
        return retrofit!!.create(PlaceHolderApi::class.java)
    }
}