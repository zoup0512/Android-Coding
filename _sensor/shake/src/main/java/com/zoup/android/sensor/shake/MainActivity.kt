package com.zoup.android.sensor.shake

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.seismic.ShakeDetector


class MainActivity : AppCompatActivity(), ShakeDetector.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sensorManager: SensorManager =
            getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(this)
        sd.start(sensorManager)
        val tv = TextView(this)
        tv.gravity = CENTER
        tv.text = "Shake me, bro!"
        setContentView(tv, RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
//        setContentView(R.layout.activity_main)
    }

    override fun hearShake() {
        Toast.makeText(this, "Don't shake me, bro!", Toast.LENGTH_SHORT).show();
    }

}