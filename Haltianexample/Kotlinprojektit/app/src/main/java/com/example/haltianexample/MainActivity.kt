package com.example.haltianexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    var laskuri = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButtonA:Button = findViewById(R.id.bu_sijaintiA)
        val myButtonB:Button = findViewById(R.id.bu_sijaintiB)
        val myButtonC:Button = findViewById(R.id.bu_sijaintiC)

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
    }



}



