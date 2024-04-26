package com.example.postapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class loginpage : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginbtn = findViewById(R.id.loginbtn)

        loginbtn.setOnClickListener {

            val enterusername = username.text.toString()
            val enterpassword = password.text.toString()

            val registerusername = intent.getStringExtra("username")
            val registerpassword = intent.getStringExtra("password")

            if (enterusername == registerusername && enterpassword == registerpassword) {

                startActivity(
                    Intent(this, Homepage::class.java).putExtra(
                        "username",
                        registerusername
                    ).putExtra("password", registerpassword)
                )
                finish()
            } else {
                startActivity(
                    Intent(this, Homepage::class.java)
                        .putExtra("result", "Login Failed: Please provide both username and password")
                )
            }
        }
    }
}