package com.example.mytestapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mytestapp.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onCreateAccount(view: View){

        var fn = binding.firstName.text
        var ln = binding.lastName.text
        var mail = binding.email.text
        var pwd = binding.password.text

        if (fn.isNotBlank() && ln.isNotBlank() && mail.isNotBlank() && pwd.isNotBlank()) {
            val user = User(fn.toString(), ln.toString(), mail.toString(), pwd.toString())

            val data = Intent()
            data.putExtra("newuser", user)
            setResult(Activity.RESULT_OK, data)
            finish()
        }else{
            Toast.makeText(this, "Please enter all field data.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}