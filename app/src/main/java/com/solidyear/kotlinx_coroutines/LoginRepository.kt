package com.solidyear.kotlinx_coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * 專案名稱:Kotlinx-coroutines
 * 類描述:
 * 建立人:user
 * 建立時間:2022/5/6 上午 09:20 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

object LoginRepository {
    private const val loginUrl = "https://randomuser.me/api/"

    //連網下載資料
    suspend fun makeLoginRequest(): String {
        val json = StringBuffer()
        //Move the execution of the coroutine to the I/O dispatcher
        withContext(Dispatchers.IO){
            val url = URL(loginUrl)

            with(url.openConnection() as HttpURLConnection){
                BufferedReader(InputStreamReader(inputStream)).readLines().forEach {
                    json.append(it)
                }
            }
        }

        Log.d(TAG, "makeLoginRequest: $json")

        return json.toString()
    }
}