package com.example.haltianexample

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : ComponentActivity() {

    private lateinit var userEmail: EditText;
    private lateinit var userPassword: EditText;

    private var url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/login-user.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        //val button: Button = findViewById(R.id.register)
        val registerButton: TextView = findViewById(R.id.register)
        val loginButton: Button = findViewById(R.id.login)

        userEmail = findViewById(R.id.email)
        userPassword = findViewById(R.id.password)

        registerButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener{
            handleLoginButtonClick()
        }

    }

    private fun handleLoginButtonClick(){

        val email  = userEmail.text.toString()
        val password  = userPassword.text.toString()

        if (email.isEmpty()) {
            userEmail.error = "Syötä sähköpostiosoite!"
            userEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            userPassword.error = "Syötä salasana!"
            userPassword.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.error = "Sähköpostiosoite ei kelpaa!"
            userEmail.requestFocus()
            return
        }

        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("salasana", password)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                try {
                    if (response.has("error")) {
                        // Handle error responses
                        val errorMessage = response.getString("error")
                        Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle successful login response
                        // You can extract additional information if needed
                        Toast.makeText(this, "Response: $response", Toast.LENGTH_LONG).show()
                    }
                } catch (e: JSONException) {
                    Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                    Log.e(ContentValues.TAG, "Error parsing JSON", e)
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
