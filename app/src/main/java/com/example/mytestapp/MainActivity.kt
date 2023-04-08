package com.example.mytestapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mytestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userOb: ArrayList<User>
    private lateinit var binding: ActivityMainBinding
    val MY_TAG = "MyApp"

    override fun onCreate(savedInstanceState: Bundle?) {

        userOb = ArrayList<User>()
        userOb.add(User("Duminda","Basnayaka","duminda@gmail.com","123"))
        userOb.add(User("Rahmath","Zada","rahmath@gmail.com","345"))
        userOb.add(User("Anish","Maharaja","anish@gmail.com","567"))
        userOb.add(User("Mahdi","Janvi","mahdi@gmail.com","789"))

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            // result object contains the intent and RESULT_OK or RESULT_CANCEL
            if(result.resultCode == Activity.RESULT_OK) {
                // result.getIntent().getdata().toString()
                val newUser = result.data?.getSerializableExtra("newuser") as User
                if (newUser != null) {
                    userOb.add(newUser)
                }
            }
            else
                binding.title.text = "Failed to get Result"
        }

        binding.createAccount.setOnClickListener {
            var intent = Intent(this,ThirdActivity::class.java)
            resultContracts.launch(intent)
        }

    }

    fun onSignIn(view: View){

        var email = binding.email.text.trim().toString();
        var password = binding.password.text.trim().toString();
        var isUserValid = false;
        var confirmedUser: User? = null

        for(user in userOb){
            if(email == user.emailId && password == user.password){
                Log.i(MY_TAG, "Confirmed User")
                confirmedUser = user;
                isUserValid = true
                break;
            }
        }
        if(isUserValid){
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("user", confirmedUser)
            startActivity(intent)
        }else{
            Log.i(MY_TAG, "Invalid User")
            Toast.makeText(this, "Incorrect username or password.", Toast.LENGTH_LONG).show()
        }

    }

    fun openGmail(view: View) {

        var email = binding.email.text.trim().toString();
        var isUserValid = false;

        for(user in userOb){
            if(email == user.emailId){
                isUserValid = true;
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse("mailto:")

                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password email")
                intent.putExtra(Intent.EXTRA_TEXT, "Your password: ${user.password}")
                intent.selector = emailIntent

                startActivity(Intent.createChooser(intent, null))
                break;
            }
        }

        if(!isUserValid){
            Log.i(MY_TAG, "User email not found")
            Toast.makeText(this, "User email not found.", Toast.LENGTH_LONG).show()
        }
    }

}