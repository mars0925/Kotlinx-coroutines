package com.solidyear.kotlinx_coroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 專案名稱:Kotlinx-coroutines
 * 類描述:
 * 建立人:user
 * 建立時間:2022/5/6 上午 09:23 */
class LoginViewModel : ViewModel() {
    val data = MutableLiveData<String>()

    fun login() {
        // 在主線程上建立coroutine
        viewModelScope.launch {
            data.value = LoginRepository.makeLoginRequest()
        }
    }
}