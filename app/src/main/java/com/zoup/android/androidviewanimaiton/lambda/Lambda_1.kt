package com.zoup.android.androidviewanimaiton.lambda

import android.content.Context
import android.view.View

class Lambda_1(private val context: Context) {

    private val button: MyButton = MyButton(context)

    fun doSomething() {

    }

    fun a() {
        button.setOnClickListener(fun(v: View): Unit {
            doSomething()
        })
    }

    fun b() {
        button.setOnClickListener(fun(v: View) {
            doSomething()
        })
    }

    fun c() {
        button.setOnClickListener({ v: View -> doSomething() })
    }

    fun d() {
        button.setOnClickListener({ v -> doSomething() })
    }

    fun e() {
        button.setOnClickListener { v ->
            doSomething()
        }
    }

    fun f() {
        button.setOnClickListener {
            doSomething()
        }
    }

    fun g() {
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                doSomething()
            }

        })
    }

    private val onClickListener: (View) -> Unit = { v: View ->
        doSomething()
    }

    private val onClickListener1 = { v: View ->
        doSomething()
    }

    private val onClickListener2: (View) -> Unit = {
        doSomething()
    }
    
    fun h() {
        button.setOnClickListener(onClickListener)
    }

}