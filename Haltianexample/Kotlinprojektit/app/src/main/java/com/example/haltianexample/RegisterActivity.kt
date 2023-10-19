package com.example.haltianexample

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
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

    private lateinit var progressbar: ProgressBar

    private var url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/sign-up-user.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userEmail = findViewById(R.id.email)
        userRegisterNumber = findViewById(R.id.registerNumber)
        userPassword = findViewById(R.id.password)
        progressbar = findViewById(R.id.progressBar)

        val registerUserButton: TextView = findViewById(R.id.btnregisterUser)

        registerUserButton.setOnClickListener{
            registerUser()
        }

    }

    private fun registerUser() {

        progressbar.visibility = View.VISIBLE

        val registerNumber  = userRegisterNumber.text.toString()
        val email  = userEmail.text.toString()
        val password  = userPassword.text.toString()
        val jsonObject = JSONObject()

        //Tsekataan onko boxeissa jotain, jos ei niin näytetään errorviesti
        if (registerNumber.isEmpty()) {
            userRegisterNumber.error = "Syötä rekisteritunnus!"
            userRegisterNumber.requestFocus()
            return
        }

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

        //katsotaan sisältääkö annettu sähköposti tarvittavat merkit esim @ jne.
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.error = "Sähköpostiosoite ei kelpaa!"
            userEmail.requestFocus()
            return
        }


        jsonObject.put("email", email)
        jsonObject.put("salasana", password)
        jsonObject.put("rekisteri", registerNumber)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                try {

                    val result = response.get("result")

                    if (result is String) {
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show()

                    }else if(result is JSONObject){
                        val errorMessage = result.getString("error")
                        Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show()

                    }

                } catch (e: JSONException) {
                    Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error parsing JSON", e)
                }finally {
                    progressbar.visibility = View.INVISIBLE
                }
            },
            { error ->
                Toast.makeText(this, "Error sending data: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("TAG", "RESPONSE IS $error")
            }
        )

// Add the request to the request queue
        Volley.newRequestQueue(this).add(request)
    }
}