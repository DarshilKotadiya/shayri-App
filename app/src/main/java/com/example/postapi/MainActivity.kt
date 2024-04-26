  package com.example.postapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var firstname : EditText
    lateinit var lastname : EditText
    lateinit var email : EditText
    lateinit var contact : EditText
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var btn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        email = findViewById(R.id.email)
        contact = findViewById(R.id.contact)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btn = findViewById(R.id.btn)


        btn.setOnClickListener {

            val enterusername = username.text.toString()
            val enterpassword = password.text.toString()

            val intent = Intent(this@MainActivity,loginpage::class.java).putExtra("username" ,enterusername).putExtra("password",enterpassword)

            startActivity(intent)
        }

        val queue = Volley.newRequestQueue(this)
        val url = "https://cdmidev.000webhostapp.com/register.php"

        val stringRequest =object : StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

            },
            Response.ErrorListener {

            }){
            override fun getParams(): MutableMap<String, String>? {

                val params = HashMap<String, String>()
                params["firstname"] = firstname.text.toString()
                params["lastname"] = lastname.text.toString()
                params["email"] = email.text.toString()
                params["contact"] = contact.text.toString()
                params["username"] = username.text.toString()
                params["password"] = password.text.toString()

                return params
            }
        }

        queue.add(stringRequest)

    }
}