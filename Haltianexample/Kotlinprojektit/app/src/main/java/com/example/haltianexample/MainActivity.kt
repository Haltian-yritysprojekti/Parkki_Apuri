package com.example.haltianexample

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    private var isCardViewAVisible = false
    private var isCardViewBVisible = false
    private var isCardViewCVisible = false
    private var url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-locations.json"
    private val originalUrl = url
    private var locationURL : String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = intent.getStringExtra("userid")
        val licensePlate = intent.getStringExtra("rekisteri")
        val electronicMail = intent.getStringExtra("email")

        supportActionBar?.hide()
        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        val myClickLayoutA : LinearLayout = findViewById(R.id.clickableLayout1)
        val myClickLayoutB : LinearLayout = findViewById(R.id.clickableLayout2)
        val myClickLayoutC : LinearLayout = findViewById(R.id.clickableLayout3)

        val emailTV : TextView = findViewById(R.id.tv_loggedEmail)
        emailTV.text = electronicMail
        val reservationIcon : ImageView = findViewById(R.id.bu_varaukset)
        val textView1: TextView = findViewById(R.id.tv_sA)
        val textView2: TextView = findViewById(R.id.tv_free_A)
        val textView3: TextView = findViewById(R.id.tv_sB)
        val textView4: TextView = findViewById(R.id.tv_free_B)
        val textView5: TextView = findViewById(R.id.tv_sC)
        val textView6: TextView = findViewById(R.id.tv_free_C)
        val locationId: TextView = findViewById(R.id.textView11a)
        val p1:TextView = findViewById(R.id.tv_1a)
        val p2:TextView = findViewById(R.id.tv_2a)
        val p3:TextView = findViewById(R.id.tv_3a)
        val p4:TextView = findViewById(R.id.tv_4a)
        val p5:TextView = findViewById(R.id.tv_5a)
        val p6:TextView = findViewById(R.id.tv_6a)
        val p7:TextView = findViewById(R.id.tv_7a)
        val p8:TextView = findViewById(R.id.tv_8a)
        val p9:TextView = findViewById(R.id.tv_9a)
        val p10:TextView = findViewById(R.id.tv_10a)
        val parkingLocation = mutableListOf<TextView>(textView1, textView3, textView5)
        val freeParkingSpots= mutableListOf<TextView>(textView2, textView4, textView6)
        val parkingSpots = mutableListOf<TextView>(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10)

        makeJsonRequest(parkingSpots,freeParkingSpots, parkingLocation)

        // Swipe refresh to refresh data in freeParkingSpots

        swipeRefreshLayout.setOnRefreshListener {
            url=originalUrl
            makeJsonRequest(parkingSpots,freeParkingSpots, parkingLocation)
            swipeRefreshLayout.postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
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

        // Gets the data from server in to variables parkingSpots when clicked
        // and shows it in cardview
        myClickLayoutA.setOnClickListener {
            isCardViewAVisible = !isCardViewAVisible

            if (isCardViewAVisible) {
                Log.i("if A isCardViewAVisible", isCardViewAVisible.toString())
                var myCardViewA: CardView = findViewById(R.id.cardView_A)
                locationId.text = "A"
                url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20A"
                locationURL = "sijainti A"
                makeJsonRequest(parkingSpots,freeParkingSpots, parkingLocation)
                myCardViewA.visibility = View.VISIBLE
                myClickLayoutB.visibility = View.GONE
                myClickLayoutC.visibility = View.GONE

                val topToBottomCardViewA = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewA.topToBottom = R.id.clickableLayout1
                myCardViewA.layoutParams = topToBottomCardViewA
            }
            else {
                Log.i("else A isCardViewAVisible", isCardViewAVisible.toString())
                val myCardViewA: CardView = findViewById(R.id.cardView_A)
                myCardViewA.visibility = View.GONE
                myClickLayoutB.visibility = View.VISIBLE
                myClickLayoutC.visibility = View.VISIBLE

                val topToBottomCardViewA = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewA.topToBottom = R.id.clickableLayout3
                myCardViewA.layoutParams = topToBottomCardViewA
            }
        }

        myClickLayoutB.setOnClickListener {
            isCardViewBVisible = !isCardViewBVisible
            if (isCardViewBVisible) {
                Log.i(" if B isCardViewAVisible", isCardViewBVisible.toString())
                var myCardViewA: CardView = findViewById(R.id.cardView_A)
                locationId.text = "B"
                url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20B"
                locationURL = "sijainti B"
                makeJsonRequest(parkingSpots,freeParkingSpots, parkingLocation)
                myCardViewA.visibility = View.VISIBLE
                myClickLayoutA.visibility = View.GONE
                myClickLayoutC.visibility = View.GONE

                val topToBottomCardViewB = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewB.topToBottom = R.id.logoView
                myCardViewA.layoutParams = topToBottomCardViewB
            }
            else {
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
                url = "https://eu-de.functions.appdomain.cloud/api/v1/web/ff38d0f2-e12e-497f-a5ea-d8452b7b4737/Parkki-apuri/get-slots.json?id=sijainti%20C"
                locationURL = "sijainti C"
                makeJsonRequest(parkingSpots,freeParkingSpots, parkingLocation)
                myClickLayoutA.visibility = View.GONE
                myClickLayoutB.visibility = View.GONE

                val topToBottomCardViewC = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewC.topToBottom = R.id.logoView
                myCardViewA.layoutParams = topToBottomCardViewC
            }

            else {
                val myCardViewA: CardView = findViewById(R.id.cardView_A)
                myCardViewA.visibility = View.GONE
                myClickLayoutA.visibility = View.VISIBLE
                myClickLayoutB.visibility = View.VISIBLE

                val topToBottomCardViewC = myCardViewA.layoutParams as ConstraintLayout.LayoutParams
                topToBottomCardViewC.topToBottom = R.id.clickableLayout2
                myCardViewA.layoutParams = topToBottomCardViewC
            }
        }

    }

    // This function handles jsonRequest to ge the data
    private fun makeJsonRequest(parkingSpots: MutableList<TextView>, freeParkingSpots: MutableList<TextView>, parkingLocation: MutableList<TextView>)
    {
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val resultArray = response.getJSONArray("result")

                    if (url == originalUrl) {
                        for (i in 0 until resultArray.length()) {
                            val jsonObject = resultArray.getJSONObject(i)
                            parkingLocation[i].text = jsonObject.getString("sijainti")
                            freeParkingSpots[i].text = jsonObject.getString("varattu")
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




}

//  parkingSpots[i].text=jsonObject.getString("tolppa")

/*vanha toimiva koodi alkaa tästä
private fun makeJsonRequest(
        parkingSpots:MutableList<TextView>,
        freeParkingSpots:MutableList<TextView>,
        parkingLocation:MutableList<TextView>) {

    // Makes the request based on url
    val request = JsonObjectRequest(
        Request.Method.GET, url, null,
        { response ->
            try {
                val resultArray = response.getJSONArray("result")

                if(url==originalUrl) {
                    for (i in 0 until resultArray.length()) {
                        val jsonObject = resultArray.getJSONObject(i)
                        parkingLocation[i].text = jsonObject.getString("sijainti")
                        freeParkingSpots[i].text = jsonObject.getString("varattu")
                    }
                }
                else{
                    var isFree = false
                    for(i in 0 until resultArray.length()){
                        val jsonObject = resultArray.getJSONObject(i)
                        parkingSpots[i].text=jsonObject.getString("idParkit")
                        val idParkitValue = jsonObject.getString("idParkit")
                        isFree = jsonObject.getBoolean("vapaa")
                        Log.d(isFree.toString(), "Value of isFree")
                        // Check if the parking spot is free (green) and set clickability accordingly
                        if(isFree){
                            parkingSpots[i].setBackgroundResource(R.drawable.parking_green)
                            parkingSpots[i].isClickable = true
                            parkingSpots[i].setOnClickListener {
                                val reservationIntent = Intent(this, ReservationActivity::class.java)
                                reservationIntent.putExtra("idParkit", idParkitValue)
                                reservationIntent.putExtra("sijainti", locationURL)
                                startActivity(reservationIntent)
                            }
                        }
                        else{
                            parkingSpots[i].setBackgroundResource(R.drawable.parking_red)
                            parkingSpots[i].isClickable=false
                            parkingSpots[i].setOnClickListener { null }
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
ja loppuu tähän */






























// Create a trust manager that does not validate certificate chains
// This method is not secure, and should be used only testing!
/*
val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
override fun getAcceptedIssuers(): Array<X509Certificate> {
    return arrayOf()
}

override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
}

override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
}
})

// Install the all-trusting trust manager
val sslContext = SSLContext.getInstance("SSL")
sslContext.init(null, trustAllCerts, SecureRandom())
HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)

// Create an ssl socket factory with our all-trusting manager
val hostnameVerifier = HostnameVerifier { _, _ -> true }
HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier)
*/