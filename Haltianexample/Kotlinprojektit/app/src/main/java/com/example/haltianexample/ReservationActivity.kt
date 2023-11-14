package com.example.haltianexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReservationActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var currentTimeJob: Job? = null
    private var userId: String? = null
    private var licensePlate: String? = null
    private var electronicMail : String? = null
    private var hack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        supportActionBar?.hide()
        val idParkitIntent: String? = intent.getStringExtra("idParkit")
        var sijainti: String? = intent.getStringExtra("sijainti")
        val exportedData = readExportedData()

        if (exportedData != null && exportedData.size >=2){
            userId = exportedData[0]
            licensePlate = exportedData[1]
            electronicMail = exportedData[2]
        }


        //Specific references to UI components
        val currentTime: TextView = findViewById(R.id.tv_currentTime)
        val varausAika: TextView = findViewById(R.id.tv_varausAika)
        val ajanLisays:Button = findViewById(R.id.bu_ResTimeAdd)
        val ajanPoisto:Button = findViewById(R.id.bu_ResTimeSub)
        val confirmButton: Button = findViewById(R.id.bu_resConf)
        val rekkariTV: TextView = findViewById(R.id.tv_licensePlate)
        val idTV : TextView = findViewById(R.id.tv_idParkit)
        val locationTV: TextView = findViewById(R.id.tv_location)
        var vaihtelevaAika = Calendar.getInstance()

        rekkariTV.text = licensePlate
        idTV.text = idParkitIntent
        locationTV.text = sijainti

        //Running clock timer
        fun updateCurrentTime(){
            val calendar = Calendar.getInstance()
            val aikaNyt = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.time)
            currentTime.text = aikaNyt
        }

        currentTimeJob = coroutineScope.launch {
            while (true){
                updateCurrentTime()
                delay(1000)
            }
        }



        //function to update reservation time display
        fun updateVarausAika(){
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            varausAika.text = sdf.format(vaihtelevaAika.time)
        }
        //Adding and removing time inside OnClickListeners
        ajanLisays.setOnClickListener{
            vaihtelevaAika.add(Calendar.MINUTE,10)
            updateVarausAika()
            hack = true
        }

        ajanPoisto.setOnClickListener {
            val currTime = Calendar.getInstance()
            val minVarausAika = Calendar.getInstance()
            minVarausAika.add(Calendar.MINUTE, 10)

            if (vaihtelevaAika.after(currTime) && vaihtelevaAika.after(minVarausAika)) {
                vaihtelevaAika.add(Calendar.MINUTE, -10)
            }
            updateVarausAika()
        }



        fun sendReservationToServer() {
            // Get the current time and reservation time
            val reaaliAika = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().time)
            val reservationTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(vaihtelevaAika.time)

            // Create a JSON object with the required structure
            val reservationObject = JSONObject()
            reservationObject.put("userid", userId)
            reservationObject.put("idParkit", idParkitIntent)
            reservationObject.put("startTime", reaaliAika)
            reservationObject.put("endTime", reservationTime)
            reservationObject.put("rekisteri", licensePlate)
            reservationObject.put("sijainti", sijainti)

            val serverUrl = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/add-reservation.json"

            val requestQueue = Volley.newRequestQueue(this)

            val request = JsonObjectRequest(Request.Method.POST, serverUrl, reservationObject,
                { response ->

                    try {
                        when (response.getString("result")) {
                            "successful" -> {
                                val confirmIntent = Intent(applicationContext, ReservationConfirm::class.java)
                                confirmIntent.putExtra("idParkit", idParkitIntent)
                                confirmIntent.putExtra("startTime", reaaliAika)
                                confirmIntent.putExtra("endTime", reservationTime)
                                confirmIntent.putExtra("rekisteri", licensePlate)
                                confirmIntent.putExtra("sijainti", sijainti)
                                startActivity(confirmIntent)
                                finish()
                                Toast.makeText(applicationContext, "Reservation successful", Toast.LENGTH_SHORT).show()
                            }
                            "spot reserved already" -> {
                                val errorObject = response.getJSONObject("result")
                                val errorMessage = errorObject.getString("error")

                                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                            else -> {

                                Toast.makeText(applicationContext, "Reservation failed with an error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: JSONException) {

                        Toast.makeText(applicationContext, "Failed to parse server response", Toast.LENGTH_SHORT).show()
                    }
                },
                { error ->

                    Toast.makeText(applicationContext, "Reservation request failed", Toast.LENGTH_SHORT).show()
                }
            )

            requestQueue.add(request)
        }

        confirmButton.setOnClickListener{
            if (!hack){
                Toast.makeText(applicationContext, "Muista lisätä varausaikaa!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sendReservationToServer()
        }
    }



    override fun onStop() {
        currentTimeJob?.cancel()
        super.onStop()
    }

    private fun readExportedData(): List<String>? {
        try {
            val fileName = "intentData.txt"
            val fileInputStream = openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val lines = mutableListOf<String>()

            var line: String?  // Change the type to String?
            while (bufferedReader.readLine().also { line = it } != null) {
                line?.let { lines.add(it) }  // Add non-null lines to the list
            }

            bufferedReader.close()

            return lines
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


}








