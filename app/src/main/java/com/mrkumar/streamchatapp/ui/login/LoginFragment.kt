package com.mrkumar.streamchatapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.mrkumar.streamchatapp.databinding.FragmentLoginBinding
import com.mrkumar.streamchatapp.model.ChatUser
import androidx.navigation.fragment.findNavController

class LoginFragment :Fragment() {

    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLoginBinding.inflate(inflater,container,false)

        binding.button.setOnClickListener {
            authenticateTheUser()
        }
        return binding.root
    }

    private fun authenticateTheUser() {
        val firstName = binding.firstNameEditText.text.toString()
        val username = binding.usernameEditText.text.toString()
        if (validateInput(firstName, binding.firstNameInputLayout) &&
            validateInput(username, binding.usernameInputLayout)
        ) {
            val chatUser = ChatUser(firstName, username)
            val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(chatUser)
            findNavController().navigate(action)
        }
    }

    private fun validateInput(inputText: String, textInputLayout: TextInputLayout): Boolean {
        return if (inputText.length <= 3) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = "* Minimum 4 Characters Allowed"
            false
        } else {
            textInputLayout.isErrorEnabled = false
            textInputLayout.error = null
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}