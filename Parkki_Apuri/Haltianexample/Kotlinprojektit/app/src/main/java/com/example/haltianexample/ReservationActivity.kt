package com.example.haltianexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import org.w3c.dom.Text
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
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
        val emailTV: TextView = findViewById(R.id.tv_email)
        val locationTV: TextView = findViewById(R.id.tv_location)
        var vaihtelevaAika = Calendar.getInstance()

        emailTV.text = electronicMail
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

        ajanLisays.setOnClickListener{
            vaihtelevaAika.add(Calendar.MINUTE,10)
            updateVarausAika()
        }

        ajanPoisto.setOnClickListener {
            // Round up to the nearest minute
            val currTime = Calendar.getInstance()
            val minVarausAika = Calendar.getInstance()
            minVarausAika.add(Calendar.MINUTE, 10)

            if (vaihtelevaAika.after(currTime) && vaihtelevaAika.after(minVarausAika)) {
                vaihtelevaAika.add(Calendar.MINUTE, -10)
            }
            updateVarausAika()
        }


        confirmButton.setOnClickListener{

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
            // Handle errors if necessary
        }

        return null
    }


}






/*
          data class Reservation(
            val userid: String,
            val idParkit: Int,
            val startTime: Long,
            val endTime: Long,
            val rekisteri: String,
            val sijainti: String
        )

 */