package com.joshhn.trelloclone.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joshhn.trelloclone.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}