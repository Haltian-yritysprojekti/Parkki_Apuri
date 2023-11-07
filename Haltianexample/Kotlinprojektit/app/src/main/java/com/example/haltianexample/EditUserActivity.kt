package com.example.haltianexample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

class EditUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        supportActionBar?.hide()

        val userid = intent.getStringExtra("userid")
        val rekisteri = intent.getStringExtra("rekisteri")
        val email = intent.getStringExtra("email")
        val salasana = intent.getStringExtra("salasana")

        val emailTV: TextView = findViewById(R.id.tv_email)
        val rekisteriTV: TextView = findViewById(R.id.tv_register)

        emailTV.text = email
        rekisteriTV.text = rekisteri
        val palausButton : ImageView = findViewById(R.id.bu_palaaMA)
        val emailEditText: EditText = findViewById(R.id.et_email)
        val rekisteriEditText: EditText = findViewById(R.id.et_register)
        val passwordEditText: EditText = findViewById(R.id.et_password)

        palausButton.setOnClickListener{
            finish()
        }

        val editButton: Button = findViewById(R.id.bu_editInformation)
        editButton.setOnClickListener {
            // Prompt the user for the current password
            val passwordPrompt = AlertDialog.Builder(this)
            passwordPrompt.setTitle("Syötä nykyinen salasanasi")

            val inputPassword = EditText(this)
            passwordPrompt.setView(inputPassword)

            passwordPrompt.setPositiveButton("OK") { dialog, which ->
                val enteredPassword = inputPassword.text.toString()
                if (enteredPassword == salasana) {
                    val requestUrl = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/put-user.json"

                    // Create a JSON object with the data
                    val jsonData = JSONObject()
                    jsonData.put("email", emailEditText.text.toString())
                    jsonData.put("salasana", passwordEditText.text.toString())
                    jsonData.put("rekisteri", rekisteriEditText.text.toString())
                    jsonData.put("userid", userid)
                    Log.e("jsonData testi", "$jsonData")

                    val request = JsonObjectRequest(
                        Request.Method.POST, requestUrl, jsonData,
                        { response ->
                            try {
                                Log.e("Response", response.toString()) // Log the entire response for debugging

                                if (response.has("result")) {
                                    val result = response.getString("result")
                                    if (result == "successful") {
                                        // Handle the success case
                                        Toast.makeText(
                                            this,
                                            "Käyttäjätiedot päivitetty onnistuneesti!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        emailTV.text = emailEditText.text.toString()
                                        rekisteriTV.text = rekisteriEditText.text.toString()
                                    } else {
                                        // Handle other cases if needed
                                        // For example, if "result" is not "successful"
                                    }
                                } else {
                                    // Handle the case where 'result' key is not found
                                    Toast.makeText(
                                        this,
                                        "No 'result' found in the server response",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } catch (e: JSONException) {
                                // Handle JSON parsing errors
                                Toast.makeText(
                                    this,
                                    "Error parsing server response: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        { error ->
                            // Handle network error
                            Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                        })

                    // Add the request to the request queue
                    Volley.newRequestQueue(this).add(request)
                } else {
                    Toast.makeText(this, "Current password does not match!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            passwordPrompt.setNegativeButton("Takaisin", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            passwordPrompt.show()
        }
    }
}