package com.example.haltianexample

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
        val adapter = ReservationsAdapter(ArrayList())

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

                        val id = reservationData.getInt("id")
                        val startTime = reservationData.getString("startTime")
                        val endTime = reservationData.getString("endTime")
                        val parkki = reservationData.getInt("parkki")
                        val rekisteri = reservationData.getString("rekisteri")
                        val sijainti = reservationData.getString("sijainti")

                        val reservation = Reservation(id, startTime, endTime, parkki, rekisteri, sijainti)
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
        val id: Int,
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

            private val deleteButton : Button = itemView.findViewById(R.id.bu_deleteButton)

            init {
                deleteButton.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val reservationToDelete = reservations[position]
                        val reservationId = reservationToDelete.id
                        Log.d("DeleteButton", "Button clicked for reservation ID: $reservationId")
                        // Call a function to delete the reservation
                        deleteReservation(itemView.context, reservationId, position)
                    }
                }
            }

        }

        // Create a new ViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.reservation_item, parent, false)
            return ReservationViewHolder(itemView)
        }

        // Set data for a ViewHolder
        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
            val currentReservation = reservations[position]

            holder.numberTextView.text = "Varaus ${currentReservation.id}"
            holder.startTimeTextView.text = "Varaus alkoi: ${currentReservation.startTime}"
            holder.endTimeTextView.text = "Varaus loppuu: ${currentReservation.endTime}"
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
            val sizeBefore = reservations.size

            // Calculate differences in the data
            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return sizeBefore
                }

                override fun getNewListSize(): Int {
                    return newReservations.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return reservations[oldItemPosition].id == newReservations[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return reservations[oldItemPosition] == newReservations[newItemPosition]
                }
            })

            // Update the data
            reservations.clear()
            reservations.addAll(newReservations)

            // Dispatch specific change events
            diffResult.dispatchUpdatesTo(this)
        }

        private fun deleteReservation(context: Context, reservationId: Int, position: Int) {
            val url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/delete-reservation.json"

            // Create a JSON object with the reservation ID to send to the server for deletion
            val jsonObject = JSONObject().apply {
                put("id", reservationId)
            }

            // Create a request to delete the reservation
            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { response ->
                    try {
                        if (response.has("result") && response.getString("result") == "successful") {
                            // Successful deletion
                            reservations.removeAt(position)
                            notifyItemRemoved(position)
                            Toast.makeText(context, "Varaus poistettu!", Toast.LENGTH_SHORT).show()

                        } else {
                            // Handle the case where deletion was not successful
                            val errorMessage = response.optString("message", "Deletion failed")
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(context, "Error parsing JSON response", Toast.LENGTH_SHORT).show()
                    }
                },
                { error ->
                    // Handle network request error
                    error.printStackTrace()
                    Toast.makeText(context, "Network request error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            )

            // Add the request to the request queue
            Volley.newRequestQueue(context).add(request)
        }

    }
}