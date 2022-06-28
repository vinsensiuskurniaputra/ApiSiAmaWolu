package com.example.apisiamawolu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apisiamawolu.R
import com.example.apisiamawolu.api.RetrofitClient
import com.example.apisiamawolu.models.LoginResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener{
            val username = inputUsername.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            if(username.isEmpty()){
                inputUsername.error = "Username Required"
                inputUsername.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                inputPassword.error = "Password Required"
                inputPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(username, password)
                .enqueue(object: Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val user = response.body()
                        Toast.makeText(applicationContext,user!!.statusCode, Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                    }

                })

        }

    }
}

