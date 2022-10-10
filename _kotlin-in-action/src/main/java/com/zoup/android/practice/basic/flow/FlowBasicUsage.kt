package com.zoup.android.practice.basic.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun simple(): Flow<Int> = flow{
    for(i in 1..3){
        delay(100)
        emit(i)
    }
}