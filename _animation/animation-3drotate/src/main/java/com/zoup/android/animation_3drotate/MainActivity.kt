package com.zoup.android.animation_3drotate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rotateImage: ImageView = findViewById(R.id.rotate_image)
        val rotateAnimation = RotateAnimation()
        rotateImage.animation = rotateAnimation
        rotateImage.setOnClickListener {
            rotateAnimation.start()
        }
    }
}