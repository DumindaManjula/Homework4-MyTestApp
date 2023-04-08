package com.example.mytestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.databinding.ActivityMainBinding
import com.example.mytestapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var user:User
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val temp = intent.getSerializableExtra("user")
        user = temp as User
        binding.header.text = "Welcome " + user.emailId
    }

}