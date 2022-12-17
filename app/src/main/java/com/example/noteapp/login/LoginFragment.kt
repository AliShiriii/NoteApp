package com.example.noteapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.databinding.FragmentLoginBinding
import com.example.noteapp.utils.Contracts.PASSWORD_ID
import com.example.noteapp.utils.Contracts.USER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login()

    }


    //login
    private fun login() {

        binding.loginButton.setOnClickListener {

            if (binding.email.text.toString() == USER_ID
                && binding.password.text.toString() == PASSWORD_ID
            ) {

               viewModel.loginUser(USER_ID, PASSWORD_ID)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSplashFragment())

            } else if (binding.email.text.toString() != USER_ID
                && binding.password.text.toString() != PASSWORD_ID
            ) {

                binding.checkEmailAddress.visibility = VISIBLE

            } else if (binding.email.text.toString().isNullOrEmpty()
                && binding.password.text.toString().isNullOrEmpty()
            ) {

                binding.checkEmailAddress.text = "Please enter your email and password!"
                binding.checkEmailAddress.visibility = VISIBLE

            }
        }
    }

}