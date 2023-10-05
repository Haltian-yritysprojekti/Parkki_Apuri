package com.example.haltianexample

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : ComponentActivity() {
    private lateinit var userEmail: EditText;
    private lateinit var userPassword: EditText;
    private lateinit var userRegisterNumber: EditText;
    private var url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/sign-up-user.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userEmail = findViewById(R.id.email)
        userRegisterNumber = findViewById(R.id.registerNumber)
        userPassword = findViewById(R.id.password)

        val registerUserButton: TextView = findViewById(R.id.btnregisterUser)

        registerUserButton.setOnClickListener{
            registerUser()
        }

    }

    private fun registerUser() {

        val registerNumber  = userRegisterNumber.text.toString()
        val email  = userEmail.text.toString()
        val password  = userPassword.text.toString()

        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("salasana", password)
        jsonObject.put("rekisteri", registerNumber)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                // Handle the response here
                try {
                    Toast.makeText(this, "Response: $response", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error parsing JSON", e)
                }
            },
            { error ->
                // Handle the error here
                Toast.makeText(this, "Error sending data: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("Error sending data", error.toString())
            }
        )

// Add the request to the request queue
        Volley.newRequestQueue(this).add(request)
    }
}