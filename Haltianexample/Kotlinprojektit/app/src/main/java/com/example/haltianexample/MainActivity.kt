package com.example.haltianexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class MainActivity : AppCompatActivity() {
    var laskuri = true
    //private lateinit var dataTextView: TextView
    private val url = "https://ec2-13-49-138-78.eu-north-1.compute.amazonaws.com:3000/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(supportActionBar != null){
            supportActionBar!!.hide()
        }
        val myButtonA:Button = findViewById(R.id.bu_sijaintiA)
        val myButtonB:Button = findViewById(R.id.bu_sijaintiB)
        val myButtonC:Button = findViewById(R.id.bu_sijaintiC)
        val textView1:TextView=findViewById(R.id.tv_sA)
        val textView2:TextView=findViewById(R.id.tv_free_A)
        val textView3:TextView=findViewById(R.id.tv_sB)
        val textView4:TextView=findViewById(R.id.tv_free_B)
        val textView5:TextView=findViewById(R.id.tv_sC)
        val textView6:TextView=findViewById(R.id.tv_free_C)
        val dataTextView1:TextView=findViewById(R.id.dataa)
        val textViews = mutableListOf<TextView>(textView1,textView3,textView5)
        val textViews1 = mutableListOf<TextView>(textView2,textView4,textView6)
        //dataTextView = findViewById(R.id.dataa)

        myButtonA.setOnClickListener{
            laskuri = !laskuri
            if (!laskuri) {
                val myImageViewA:ImageView = findViewById(R.id.ivParkA)
                myImageViewA.visibility = View.VISIBLE
            }
            else{
                val myImageViewA:ImageView = findViewById(R.id.ivParkA)
                myImageViewA.visibility = View.GONE
            }

        }
        myButtonB.setOnClickListener{
            laskuri = !laskuri
            if(!laskuri){
                val myImageViewB:ImageView = findViewById(R.id.ivParkB)
                myImageViewB.visibility = View.VISIBLE
            }
            else{
                val myImageViewB:ImageView = findViewById(R.id.ivParkB)
                myImageViewB.visibility = View.GONE
            }
        }
        myButtonC.setOnClickListener{
            laskuri = !laskuri
            if(!laskuri){
                val myImageViewC:ImageView = findViewById(R.id.ivParkC)
                myImageViewC.visibility = View.VISIBLE
            }
            else{
                val myImageViewC:ImageView = findViewById(R.id.ivParkC)
                myImageViewC.visibility = View.GONE
            }
        }


        // Create a trust manager that does not validate certificate chains
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

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    for(i in 0 until response.length()){
                        val jsonObject = response.getJSONObject(i)
                        textViews[i].text = jsonObject.getString("sijainti")
                        textViews1[i].text = jsonObject.getString("vapaa")
                        }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error retrieving data:${error.message}", Toast.LENGTH_LONG).show()
                Log.e("Error retrieving data", error.toString())

            }
        )
        Volley.newRequestQueue(this).add(request)

    }



}



