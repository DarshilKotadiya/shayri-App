package com.example.postapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Homepage : AppCompatActivity() {

    lateinit var uname: TextView
    lateinit var pass: TextView
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        uname = findViewById(R.id.uname)
        pass = findViewById(R.id.pass)
        result = findViewById(R.id.result)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val resultt = intent.getStringExtra("result")

        uname.setText("username : $username")
        pass.setText("password : $password")
        result.setText("result : $resultt")
    }
}