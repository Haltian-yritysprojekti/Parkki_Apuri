package com.example.haltianexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ReservationsView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations_view)
        supportActionBar?.hide()

        // Close the ReservationsView
        val closeButton: Button = findViewById(R.id.bu_palaa)
        closeButton.setOnClickListener {
            finish()
        }

        val userId = intent.getStringExtra("userid")
        val url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-reservation.json?userid=$userId"

        // Create a RecyclerView and set its layout manager
        val recyclerView = findViewById<RecyclerView>(R.id.rv_reservation)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create an empty adapter for Reservation items
        val adapter = ReservationsAdapter(ArrayList<ReservationsView.Reservation>())

        // Fetch reservation data and create a list of Reservation objects
        fetchReservations(url) { reservations ->
            // Update the adapter with the fetched reservations and notify the RecyclerView
            adapter.updateReservations(reservations)
            recyclerView.adapter = adapter
        }
    }

    // Function to fetch and parse reservation data
    private fun fetchReservations(url: String, callback: (List<Reservation>) -> Unit) {
        val reservations = ArrayList<Reservation>()

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val resultArray = response.getJSONArray("result")

                    for (i in 0 until resultArray.length()) {
                        val reservationData = resultArray.getJSONObject(i)

                        val startTime = reservationData.getString("startTime")
                        val endTime = reservationData.getString("endTime")
                        val parkki = reservationData.getInt("parkki")
                        val rekisteri = reservationData.getString("rekisteri")
                        val sijainti = reservationData.getString("sijainti")

                        val reservation = Reservation(i + 1, startTime, endTime, parkki, rekisteri, sijainti)
                        reservations.add(reservation)
                    }

                    // Call the callback function and pass the fetched reservations
                    callback(reservations)

                } catch (e: JSONException) {
                    // Handle JSON parsing error
                    e.printStackTrace()
                }
            },
            { error ->
                // Handle network request error
                error.printStackTrace()
                Toast.makeText(this, "Network request error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        // Add the request to the request queue
        Volley.newRequestQueue(this).add(request)
    }

    // Data class for storing reservation information
    data class Reservation(
        val number: Int,
        val startTime: String,
        val endTime: String,
        val parkki: Int,
        val rekisteri: String,
        val sijainti: String
    )

    // Adapter class to manage the RecyclerView for reservations
    inner class ReservationsAdapter(private val reservations: ArrayList<Reservation>) :
        RecyclerView.Adapter<ReservationsAdapter.ReservationViewHolder>() {

        // ViewHolder class for an individual reservation
        inner class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)
            val startTimeTextView: TextView = itemView.findViewById(R.id.startTimeTextView)
            val endTimeTextView: TextView = itemView.findViewById(R.id.endTimeTextView)
            val parkkiTextView: TextView = itemView.findViewById(R.id.parkkiTextView)
            val rekisteriTextView: TextView = itemView.findViewById(R.id.rekisteriTextView)
            val sijaintiTextView: TextView = itemView.findViewById(R.id.sijaintiTextView)
        }

        // Create a new ViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.reservation_item, parent, false)
            return ReservationViewHolder(itemView)
        }

        // Set data for a ViewHolder
        override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
            val currentReservation = reservations[position]

            holder.numberTextView.text = "Varaus ${currentReservation.number}"
            holder.startTimeTextView.text = "Varaus alkoi: ${currentReservation.startTime}"
            holder.endTimeTextView.text = "Varaus loppui: ${currentReservation.endTime}"
            holder.parkkiTextView.text = "Parkkipaikka: ${currentReservation.parkki}"
            holder.rekisteriTextView.text = "Rekisteri: ${currentReservation.rekisteri}"
            holder.sijaintiTextView.text = "Sijainti: ${currentReservation.sijainti}"
        }

        // Return the number of reservations
        override fun getItemCount(): Int {
            return reservations.size
        }

        // Function to update reservation data
        fun updateReservations(newReservations: List<Reservation>) {
            reservations.clear()
            reservations.addAll(newReservations)
            notifyDataSetChanged()
        }
    }
}