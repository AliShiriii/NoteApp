package com.example.noteapp.signUp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.databinding.FragmentSignUpBinding
import com.example.noteapp.utils.Contracts.PASSWORD_ID
import com.example.noteapp.utils.Contracts.USER_ID
import com.example.repository.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var pref: SharedPreferences

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveEmailAndPasswordToSharedPreference()
        onClickLoginButton()

    }

    private fun saveEmailAndPasswordToSharedPreference(){

        pref.edit().putString(USER_ID, binding.emailSignUp.text.toString()).apply()
        pref.edit().putString(PASSWORD_ID, binding.passwordSignUp.text.toString()).apply()

    }

    private fun onClickLoginButton() {

        binding.signUp.setOnClickListener {

            if (binding.emailSignUp.text.toString().trim().isNullOrEmpty()
                && binding.passwordSignUp.text.toString().trim().isNullOrEmpty()
            ) {

                Toast.makeText(requireContext(), "please enter your email and password", Toast.LENGTH_SHORT).show()

            } else if (binding.passwordSignUp.text.toString() != binding.confirmPassword.text.toString()) {

                Toast.makeText(requireContext(), "please check your password", Toast.LENGTH_SHORT).show()

            } else {

                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())

                viewModel.signUpUser(
                    User(
                        email = binding.emailSignUp.text.toString(),
                        password = binding.passwordSignUp.text.toString(),
                        confirmPassword = binding.confirmPassword.text.toString()
                    )
                )

            }

        }
    }
}