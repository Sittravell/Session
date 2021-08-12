package com.sittravell.session

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sittravell.session.databinding.HomeLayoutBinding

class Home: AppCompatActivity() {
    lateinit var binding: HomeLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //IMPORTANT! Always initialize Session in onCreate
        Session.setup(this)

        // If user is logged in , then go to Login screen
        if(!Session.isLoggedIn){
            goToLogin()
        }

        with(binding){
            //If no name is stored in Session, then we will hide the name text view.
            yourNameContainer.visibility = if(Session.name == Session.NO_NAME) View.GONE else View.VISIBLE
            storedName.text = if(Session.name == Session.NO_NAME) "" else Session.name

            remember.setOnClickListener { remember() }
            logout.setOnClickListener { logout() }
        }
    }

    fun remember(){
        with(binding){
            // Reset error message if previous attempt failed
            name.helperText = ""
            var inputName = name.editText?.text.toString()
            var valid = true

            if(inputName.isNullOrBlank()){
                valid = false
                name.helperText = "Name is required"
            }

            if(valid){
                //display your name
                binding.storedName.text = inputName
                yourNameContainer.visibility = View.VISIBLE

                // Your name will be remembered
                Session.name = inputName
            }
        }
    }

    fun goToLogin(){
        //Lets bring the user to Login screen
        val intent = Intent(this, Login::class.java)

        // Avoid user going back to login screen by back button
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(intent)

        // Close current activity
        finish()
    }

    fun logout(){
        // Forgets user and removes all information and go to Login screen
        Session.finish()
        goToLogin()
    }
}