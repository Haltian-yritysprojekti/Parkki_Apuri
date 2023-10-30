package com.example.haltianexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReservationActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var currentTimeJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val userId = intent.getStringExtra("userid")
        val licensePlate = intent.getStringExtra("rekisteri")

        data class Reservation(
            val userid: String,
            val idParkit: Int,
            val startTime: Long,
            val endTime: Long,
            val rekisteri: String,
            val sijainti: String
        )

        //Specific references to UI components

        val currentTime: TextView = findViewById(R.id.tv_currentTime)
        val varausAika: TextView = findViewById(R.id.tv_varausAika)
        val ajanLisays:Button = findViewById(R.id.bu_ResTimeAdd)
        val ajanPoisto:Button = findViewById(R.id.bu_ResTimeSub)
        val confirmButton: Button = findViewById(R.id.bu_resConf)
        val rekkariTV: TextView = findViewById(R.id.tv_licensePlate)
        var vaihtelevaAika = Calendar.getInstance()

        rekkariTV.text = "$licensePlate"
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

}