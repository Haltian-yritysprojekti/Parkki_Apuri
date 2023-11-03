package com.example.haltianexample

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {

    private var isCardViewAVisible = false
    private var isCardViewBVisible = false
    private var isCardViewCVisible = false

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

    private var url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-locations.json"
    private val originalUrl = url

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

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


        // get reference to the string array that we just created
        val languages = resources.getStringArray(R.array.locations)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)

        autocompleteTV.setOnItemClickListener { parent, view, position, id ->
            val selectedLocation = parent?.getItemAtPosition(position).toString()
            Log.d("Selected Location", selectedLocation)
            getParkingSpots(selectedLocation)
            //if (selectedLocation == "Oulun yliopisto"){

            //}
            //showParkingAreas(selectedLocation)
            //createParkingAreaCard()
        }


        /*private fun showParkingAreas(selectedLocation: String){
        val parkingAreaLayout: LinearLayout = findViewById(R.id.parkingAreasLayout)
        parkingAreaLayout.visibility = View.VISIBLE
    }

     */


        //val myClickLayoutA: LinearLayout = findViewById(R.id.clickableLayout1)
        //val myClickLayoutB: LinearLayout = findViewById(R.id.clickableLayout2)
        //val myClickLayoutC: LinearLayout = findViewById(R.id.clickableLayout3)

        //val textView1: TextView = findViewById(R.id.tv_sA)
        //val textView2: TextView = findViewById(R.id.tv_free_A)
        //val textView3: TextView = findViewById(R.id.tv_sB)
        //val textView4: TextView = findViewById(R.id.tv_free_B)
        //val textView5: TextView = findViewById(R.id.tv_sC)
        //val textView6: TextView = findViewById(R.id.tv_free_C)
        val locationId: TextView = findViewById(R.id.textView11a)
        //val parkingLocation = mutableListOf<TextView>(textView1, textView3, textView5)
        //val freeParkingSpots = mutableListOf<TextView>(textView2, textView4, textView6)

        makeJsonRequest(parkingSpots)

        // Swipe refresh to refresh data in freeParkingSpots

        swipeRefreshLayout.setOnRefreshListener {
            url = originalUrl
            makeJsonRequest(parkingSpots)
            swipeRefreshLayout.postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }


        // Gets the data from server in to variables parkingSpots when clicked
        // and shows it in cardview

        /*
        myClickLayoutB.setOnClickListener {
            isCardViewBVisible = !isCardViewBVisible
            if (isCardViewBVisible) {
                Log.i(" if B isCardViewAVisible", isCardViewBVisible.toString())
                var myCardViewA: CardView = findViewById(R.id.cardView_A)
                locationId.text = "B"
                url =
                    "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20B"
                makeJsonRequest(parkingSpots, freeParkingSpots, parkingLocation)
                myCardViewA.visibility = View.VISIBLE
                myClickLayoutA.visibility = View.GONE
                myClickLayoutC.visibility = View.GONE

                val topToBottomCardViewB = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewB.topToBottom = R.id.logoView
                myCardViewA.layoutParams = topToBottomCardViewB
            } else {
                Log.i("else B isCardViewAVisible", isCardViewBVisible.toString())
                val myCardViewA: CardView = findViewById(R.id.cardView_A)
                myCardViewA.visibility = View.GONE
                myClickLayoutA.visibility = View.VISIBLE
                myClickLayoutC.visibility = View.VISIBLE

                val topToBottomCardViewB = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewB.topToBottom = R.id.clickableLayout1
                myCardViewA.layoutParams = topToBottomCardViewB
            }
        }
        myClickLayoutC.setOnClickListener {
            isCardViewCVisible = !isCardViewCVisible
            if (isCardViewCVisible) {
                val myCardViewA: CardView = findViewById(R.id.cardView_A)
                locationId.text = "C"
                myCardViewA.visibility = View.VISIBLE
                url =
                    "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20C"
                makeJsonRequest(parkingSpots, freeParkingSpots, parkingLocation)
                myClickLayoutA.visibility = View.GONE
                myClickLayoutB.visibility = View.GONE

                val topToBottomCardViewC = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewC.topToBottom = R.id.logoView
                myCardViewA.layoutParams = topToBottomCardViewC
            } else {
                val myCardViewA: CardView = findViewById(R.id.cardView_A)
                myCardViewA.visibility = View.GONE
                myClickLayoutA.visibility = View.VISIBLE
                myClickLayoutB.visibility = View.VISIBLE

                val topToBottomCardViewC = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewC.topToBottom = R.id.clickableLayout2
                myCardViewA.layoutParams = topToBottomCardViewC
            }
        }

         */

    }

    private fun getParkingSpots(selectedLocation: String) {

        url = when (selectedLocation){
            "Oulun yliopisto" -> "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20A"
            "Teknologia kylä" -> "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20B"
            "Kontinkankaan kampus" -> "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20C"
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


        /*

            isCardViewAVisible = !isCardViewAVisible


                Log.i("if A isCardViewAVisible", isCardViewAVisible.toString())
                var myCardViewA: CardView = findViewById(R.id.cardView_A)
                //locationId.text = "A"
                makeJsonRequest(parkingSpots)
                myCardViewA.visibility = View.VISIBLE
                //myClickLayoutB.visibility = View.GONE
                //myClickLayoutC.visibility = View.GONE

                val topToBottomCardViewA = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewA.topToBottom = R.id.textInputLayout
                myCardViewA.layoutParams = topToBottomCardViewA

                Log.i("else A isCardViewAVisible", isCardViewAVisible.toString())
                val myCardViewA: CardView = findViewById(R.id.cardView_A)
                myCardViewA.visibility = View.GONE
                //myClickLayoutB.visibility = View.VISIBLE
                //myClickLayoutC.visibility = View.VISIBLE

                val topToBottomCardViewA = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewA.topToBottom = R.id.textInputLayout
                myCardViewA.layoutParams = topToBottomCardViewA

         */


    }


    // This function handles jsoRequest to ge the data
        private fun makeJsonRequest(parkingSpots: List<TextView>) {

            // Makes the request based on url
            val request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {
                        val resultArray = response.getJSONArray("result")

                        if(url==originalUrl) {
                            for (i in 0 until resultArray.length()) {
                                val jsonObject = resultArray.getJSONObject(i)
                                //parkingLocation[i].text = jsonObject.getString("sijainti")
                                //freeParkingSpots[i].text = jsonObject.getString("varattu")
                            }
                        }
                        else{
                            var isFree = false
                            for(i in 0 until resultArray.length()){
                                val jsonObject = resultArray.getJSONObject(i)
                                parkingSpots[i].text=jsonObject.getString("idParkit")
                                isFree = jsonObject.getBoolean("vapaa")
                                Log.d(isFree.toString(), "Value of isFree")
                                if(isFree){
                                    parkingSpots[i].setBackgroundResource(R.drawable.parking_green)
                                }
                                else{
                                    parkingSpots[i].setBackgroundResource(R.drawable.parking_red)
                                }
                            }
                        }

                    } catch (e: Exception) {
                        Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "Error message", e)
                    }
                },
                { error ->
                    Toast.makeText(this, "Error retrieving data:${error.message}", Toast.LENGTH_LONG)
                        .show()
                    Log.e("Error retrieving data", error.toString())
                }
            )
            Volley.newRequestQueue(this).add(request)

        }
}