package com.zoup.android.kotlin.coroutines


import java.util.*

fun test(){}

class FunA()

fun sum(a: Int, b: Int):Int{
    return a + b;
}

fun sum1(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}


//可变长参数函数
fun vars(vararg v : Int){
    for(vt in v){
        print(vt)
    }
}

fun main(args: Array<String>) {
    println("Hello Kotlin");
    vars(1,2,3,4,5);
}
