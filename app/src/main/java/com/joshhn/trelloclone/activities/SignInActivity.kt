package com.joshhn.trelloclone.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joshhn.trelloclone.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private var binding: ActivitySignInBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}