package com.zoup.android.animation_3drotate

import android.graphics.Camera
import android.graphics.Matrix
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 *@Created by zoup on 2021/12/21.
 */

class RotateAnimation : Animation() {
    lateinit var mCamera: Camera
    private var mCenterX: Float = 0.0f
    private var mCenterY: Float = 0.0f

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCamera = Camera()
        mCenterX = (width / 2).toFloat()
        mCenterY = (height / 2).toFloat()
        duration = 1000
        repeatCount= INFINITE
        repeatMode= RESTART
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        var degreeY = interpolatedTime * 180
        var matrix = t?.matrix
        mCamera.save()
        mCamera.rotateY(degreeY)
        mCamera.getMatrix(matrix)
        mCamera.restore()
        matrix?.preTranslate(-mCenterX, -mCenterY)
        matrix?.postTranslate(mCenterX, mCenterY)
    }
}