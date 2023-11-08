package com.example.haltianexample

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {

    private var url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-locations.json"
    private val originalUrl = url
    private var locationURL : String? = null

    private lateinit var p1: TextView
    private lateinit var p2: TextView
    private lateinit var p3: TextView
    private lateinit var p4: TextView
    private lateinit var p5: TextView
    private lateinit var p6: TextView
    private lateinit var p7: TextView
    private lateinit var p8: TextView
    private lateinit var p9: TextView
    private lateinit var p10:TextView

    private lateinit var parkingSpots: List<TextView>
    //private lateinit var parkingLocation: List<TextView>
    //private lateinit var freeParkingSpots: List<TextView>
    private lateinit var locationId: TextView
    private lateinit var launcher: ActivityResultLauncher<Intent>

    private val combinedList = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val userId = intent.getStringExtra("userid")
        val licensePlate = intent.getStringExtra("rekisteri")
        var electronicMail = intent.getStringExtra("email")
        val salasana = intent.getStringExtra("salasana")

        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        //val myClickLayoutA : LinearLayout = findViewById(R.id.clickableLayout1)
        //val myClickLayoutB : LinearLayout = findViewById(R.id.clickableLayout2)
        //val myClickLayoutC : LinearLayout = findViewById(R.id.clickableLayout3)

        val emailTV : TextView = findViewById(R.id.tv_loggedEmail)
        emailTV.text = electronicMail
        val reservationIcon : ImageView = findViewById(R.id.bu_varaukset)
        val editUserInfoIcon : ImageView = findViewById(R.id.bu_kayttajaMuokkaus)


        //val locationId: TextView = findViewById(R.id.textView11a)
        locationId = findViewById(R.id.textView11a)
        p1 = findViewById(R.id.tv_1a)
        p2 = findViewById(R.id.tv_2a)
        p3 = findViewById(R.id.tv_3a)
        p4 = findViewById(R.id.tv_4a)
        p5 = findViewById(R.id.tv_5a)
        p6 = findViewById(R.id.tv_6a)
        p7 = findViewById(R.id.tv_7a)
        p8 = findViewById(R.id.tv_8a)
        p9 = findViewById(R.id.tv_9a)
        p10 = findViewById(R.id.tv_10a)
        parkingSpots = listOf(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
        //freeParkingSpots = listOf(textView1,textView3, textView5)
        //parkingLocation = listOf(textView2, textView4, textView6)

        //val languages = resources.getStringArray(R.array.locations)

        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, combinedList)

        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        autocompleteTV.setAdapter(arrayAdapter)

        autocompleteTV.setOnItemClickListener { parent, view, position, id ->
            val selectedLocation = parent?.getItemAtPosition(position).toString()
            Log.d("Selected Location", selectedLocation)
            getParkingSpots(selectedLocation)
        }

        //setting up ActivityResultLauncher to get result as updatedEmail from EditUserActivity
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val updatedEmail = data?.getStringExtra("updatedEmail")
                if (updatedEmail != null) {
                    emailTV.text = updatedEmail
                    electronicMail = updatedEmail
                }
            }
        }


        makeJsonRequest(parkingSpots)

        // Swipe refresh to refresh data in freeParkingSpots

        swipeRefreshLayout.setOnRefreshListener {
            url=originalUrl
            makeJsonRequest(parkingSpots)
            swipeRefreshLayout.postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }

        editUserInfoIcon.setOnClickListener{
            val editIntent = Intent(this, EditUserActivity::class.java)
            editIntent.putExtra("userid", userId)
            editIntent.putExtra("rekisteri", licensePlate)
            editIntent.putExtra("email", electronicMail)
            editIntent.putExtra("salasana", salasana)
            launcher.launch(editIntent)
        }


        reservationIcon.setOnClickListener{
            val resIntent = Intent(this, ReservationsView::class.java)
            resIntent.putExtra("userid", userId)
            startActivity(resIntent)
        }
        //exports intents gathered from LoginActivity.kt
        fun exportIntentToFile(userId: String?, licensePlate: String?, electronicMail: String?){
            val data = "$userId\n$licensePlate\n$electronicMail"

            try {
                val fileName = "intentData.txt"
                val fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
                fileOutputStream.close()
            }   catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error exporting data", Toast.LENGTH_SHORT).show()
            }
        }
        exportIntentToFile(userId, licensePlate, electronicMail)

    }



    private fun getParkingSpots(selectedLocation: String) {
        val regex = Regex("sijainti [A-Z]")
        val match = regex.find(selectedLocation)
        val locationValue = match?.value ?: "Default Value"
        locationURL = locationValue

        url = when (locationValue) {

            "sijainti A" -> {
                locationId.text = "A"
                "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20A"
            }
            "sijainti B" -> {
                locationId.text = "B"
                "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20B"
            }
            "sijainti C" -> {
                locationId.text = "C"
                "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20C"
            }
            else -> ({
                url = originalUrl
            }).toString()
        }
        makeJsonRequest(parkingSpots)
        var cardViewA: CardView = findViewById(R.id.cardView_A)
        cardViewA.visibility = View.VISIBLE
        val topToBottomCardViewA = cardViewA.layoutParams as ConstraintLayout.LayoutParams
        topToBottomCardViewA.topToBottom = R.id.textInputLayout
        cardViewA.layoutParams = topToBottomCardViewA
    }



    // This function handles jsonRequest to ge the data
    private fun makeJsonRequest(parkingSpots: List<TextView>)
    {
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val resultArray = response.getJSONArray("result")

                    if (url == originalUrl) {
                        combinedList.clear()
                        for (i in 0 until resultArray.length()) {
                            val jsonObject = resultArray.getJSONObject(i)
                            val location = jsonObject.getString("sijainti")
                            val availableSpots = jsonObject.getString("varattu")

                            val combinedItem = "$location - Vapaana $availableSpots paikkaa"
                            combinedList.add(combinedItem)

                        }
                    } else {
                        for (i in 0 until resultArray.length()) {
                            val jsonObject = resultArray.getJSONObject(i)
                            parkingSpots[i].text = jsonObject.getString("idParkit")
                            val idParkitValue = jsonObject.getString("idParkit")
                            val isFree = jsonObject.getBoolean("vapaa")
                            val tolppa = jsonObject.getInt("tolppa")

                            // Check if the parking spot is free or an electric spot
                            if (isFree) {
                                if (tolppa == 1) {
                                    // The spot is for electric cars and is free, set the electric green drawable
                                    parkingSpots[i].setBackgroundResource(R.drawable.parking_green_e)
                                } else {
                                    // The spot is not for electric cars but is free, set the regular green drawable
                                    parkingSpots[i].setBackgroundResource(R.drawable.parking_green)
                                }
                                parkingSpots[i].isClickable = true
                                parkingSpots[i].setOnClickListener {
                                    val reservationIntent = Intent(this, ReservationActivity::class.java)
                                    reservationIntent.putExtra("idParkit", idParkitValue)
                                    reservationIntent.putExtra("sijainti", locationURL)
                                    startActivity(reservationIntent)
                                }
                            } else {
                                // The spot is not free, set the red drawable and disable clickability
                                parkingSpots[i].setBackgroundResource(R.drawable.parking_red)
                                parkingSpots[i].isClickable = false
                                parkingSpots[i].setOnClickListener { null }
                                if (tolppa == 1) {
                                    // The spot is for electric cars and not free, set a specific electric occupied drawable
                                    parkingSpots[i].setBackgroundResource(R.drawable.parking_red_e)
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error message", e)
                }
            },
            { error ->
                Toast.makeText(this, "Error retrieving data: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("Error retrieving data", error.toString())
            }
        )
        // Add the request to the request queue
        Volley.newRequestQueue(this).add(request)
    }


    override fun onBackPressed() {
            showExitConfirmationDialog()
        }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Varmistus")
        builder.setMessage("Oletko varma että haluat poistua?")
        builder.setPositiveButton("Kyllä") { dialog, which ->
            finish()
        }
        builder.setNegativeButton("Ei") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}



