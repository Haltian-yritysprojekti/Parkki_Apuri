package com.example.haltianexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ReservationConfirm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_confirm)
        supportActionBar?.hide()


        val idParkit = intent.getStringExtra("idParkit")
        val rekisteri = intent.getStringExtra("rekisteri")
        val startTime = intent.getStringExtra("startTime")
        val endTime = intent.getStringExtra("endTime")
        val sijainti = intent.getStringExtra("sijainti")


        val tvStartTime : TextView = findViewById(R.id.tv_varausAika)
        tvStartTime.text = startTime
        val tvEndTime : TextView = findViewById(R.id.tv_licensePlate)
        tvEndTime.text = endTime
        val tvparkkipaikka : TextView = findViewById(R.id.tv_08)
        tvparkkipaikka.text = idParkit
        val tvrekisteri : TextView = findViewById(R.id.tv_09)
        tvrekisteri.text = rekisteri
        val tvsijainti : TextView = findViewById(R.id.tv_10)
        tvsijainti.text = sijainti
        val returnButton : Button = findViewById(R.id.bu_return)

        returnButton.setOnClickListener {
            finish()
        }

    }


}