package com.example.apisiamawolu.api

import com.example.apisiamawolu.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("student")
    fun userLogin(
        @Field("greet") greet:String,
        @Field("name") name:String
    ): Call<LoginResponse>

}
