package com.example.haltianexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReservationsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations_view)
        val userId = intent.getStringExtra("userid")
        val url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-reservation.json?userid=$userId"

        



    }
}