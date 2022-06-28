package com.example.apisiamawolu.api

import android.util.Base64
import com.example.apisiamawolu.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val AUTH = "Basic"+ Base64.encodeToString("belalkhan:123456".toByteArray(), Base64.NO_WRAP)

    private const val BASE_URL = "https://api.erpeelisme.my.id/api/v1"

    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method(),original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }

}