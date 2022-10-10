package com.zoup.android.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @link https://www.jianshu.com/p/6ecf6da3286f
 */
class FlowUsage()

    fun simple(): Flow<Int> = flow { // 流构建器
        for (i in 1..6) {
            delay(100) // 假装我们在这里做了一些有用的事情
            emit(i) // 发送下一个值
        }
    }

    fun main(args: Array<String>) = runBlocking<Unit> {
        // 启动并发的协程以验证主线程并未阻塞
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        // 收集这个流
        simple().collect { value -> println(value) }

    }

