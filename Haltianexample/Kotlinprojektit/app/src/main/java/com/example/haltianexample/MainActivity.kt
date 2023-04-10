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


class MainActivity : AppCompatActivity() {
    var laskuri = true
    private lateinit var dataTextView: TextView
    private val url = "https://www.jsonkeeper.com/b/0RH6"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButtonA:Button = findViewById(R.id.bu_sijaintiA)
        val myButtonB:Button = findViewById(R.id.bu_sijaintiB)
        val myButtonC:Button = findViewById(R.id.bu_sijaintiC)
        dataTextView = findViewById(R.id.dataa)

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

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONArray> { response ->
                try {
                    val jsonObject = response.getJSONObject(0)
                    val languageName = jsonObject.getString("languageName")
                    dataTextView.text = languageName
                } catch (e: Exception) {
                    Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error retrieving data:${error.message}", Toast.LENGTH_LONG).show()
                Log.e("Error retrieving data", error.toString())

            }
        )
        Volley.newRequestQueue(this).add(request)

    }



}



