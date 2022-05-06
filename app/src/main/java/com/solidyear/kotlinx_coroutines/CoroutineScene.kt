package com.solidyear.kotlinx_coroutines

import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*

/**
 * 專案名稱:Kotlinx-coroutines
 * 類描述:
 * 建立人:user
 * 建立時間:2022/5/6 下午 04:37 */
object CoroutineScene {

    /**
     * 啟動三個子線程，並且同步拿到他們的返回值，進而更新ui
     *
     */
    private suspend fun startScene1(): String {

        val str = StringBuffer()
        str.append(request1() + ",")
        str.append(request2() + ",")
        str.append(request3())

        Log.d(TAG, "startScene1: $str")

        return str.toString()

    }

    suspend fun updateUI(view: View) {
        (view as TextView).text = startScene1()
        Log.d(TAG, "updateUI set result")
    }


    private suspend fun request1(): String {
        delay(2000)
        Log.d(TAG, "request1 work on : ${Thread.currentThread().name}")
        return "result from request1"
    }

    private suspend fun request2(): String {
        delay(2000)
        Log.d(TAG, "request2 work on : ${Thread.currentThread().name}")
        return "result from request2"
    }

    private suspend fun request3(): String {
        delay(2000)
        Log.d(TAG, "request3 work on : ${Thread.currentThread().name}")
        return "result from request3"
    }

    /** 啟動一個協程 先執行request1 在同時執行request2 以及request3 然後再更新uI*/
    suspend fun startScene2(){
            request1()
            Log.d(TAG, "startScene2: coroutine is starting")

            val deferred2 = GlobalScope.async { request2() }
            val deferred3 = GlobalScope.async { request3() }

            updateUI(deferred2.await(),deferred3.await())



    }

    private fun updateUI(result2: String, result3: String) {
        Log.d(TAG, "updateUI: work on ${Thread.currentThread().name}")
        Log.d(TAG, "updateUI: $result2, $result3")
    }
}