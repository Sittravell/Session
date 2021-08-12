package com.sittravell.session

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sittravell.session.databinding.LoginLayoutBinding

class Login : AppCompatActivity()  {
    lateinit var binding: LoginLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginLayoutBinding.inflate(layoutInflater)
        binding.login.setOnClickListener { login() }

        setContentView(binding.root)

        // IMPORTANT! Always initialize session in onCreate
        Session.setup(this)

        //If user is logged in, then go to Home screen
        if(Session.isLoggedIn){
            goToHome()
        }
    }

    fun goToHome(){
        //Lets bring the user to Home screen
        val intent = Intent(this@Login, Home::class.java)

        // Avoid user going back to login screen by back button
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(intent)

        // Close current activity
        finish()
    }

    fun login(){
        with(binding){
            // Remove error message if show in previous attempt
            username.helperText = ""
            password.helperText = ""

            var uname = binding.username.editText?.text.toString()
            var pass = binding.password.editText?.text.toString()
            var valid = true
            if(uname.isNullOrBlank()){
                username.helperText = "Username is required"
                valid = false
            }
            if(pass.isNullOrBlank()){
                password.helperText = "Password is required"
                valid = false
            }
            if(!valid){
                // Do not validate further, if we already have error
                return
            }
            if(uname != "onichannn" || pass != "baka_hentai!"){
                password.helperText = "Username or Password invalid"
                valid = false
            }
            if(valid){
                //Start a session to remember this user and go to Home screen
                Session.start(uname)

                goToHome()
            }
        }

    }
}