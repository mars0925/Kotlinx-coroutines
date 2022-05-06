package com.solidyear.kotlinx_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.solidyear.kotlinx_coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val TAG = "mars"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.download.setOnClickListener {
            loginViewModel.login()
        }

        loginViewModel.data.observe(this) {
            binding.result.text = it
            Log.d(TAG, "onCreate: $it")
        }

        binding.startScene1.setOnClickListener {
            lifecycleScope.launch {
                CoroutineScene.updateUI(binding.result)
                Log.d(TAG, "mainActivity set result")
            }
        }


        binding.startScene2.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                CoroutineScene.startScene2()
            }
        }

    }
}