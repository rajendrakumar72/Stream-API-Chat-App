package com.mrkumar.streamchatapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.mrkumar.streamchatapp.R
import com.mrkumar.streamchatapp.model.ChatUser
import com.mrkumar.streamchatapp.ui.login.LoginFragmentDirections
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.name

class MainActivity : AppCompatActivity() {
    private  lateinit var navController: NavController
    private val client=ChatClient.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController=findNavController(R.id.navHostFragment)

        if (navController.currentDestination?.label.toString().contains("login")){
            val currentUser=client.getCurrentUser()
            if (currentUser!=null){
                val user = ChatUser(currentUser.name, currentUser.id)
                val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(user)
                navController.navigate(action)

            }
        }
    }
}