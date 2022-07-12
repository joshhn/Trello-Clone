package com.joshhn.trelloclone.activities

import android.os.Bundle
import com.joshhn.trelloclone.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private var  binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}