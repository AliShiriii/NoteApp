package com.example.noteapp.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import java.util.*
import kotlin.concurrent.timerTask

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToSignUp()


    }

    private fun goToSignUp() {

        helpTextGenerator(1000, "create your note..")
        helpTextGenerator(2000, "update your note..")
        helpTextGenerator(3000, "delete your note..")
        helpTextGenerator(4000, "save your note..")

        var textArray = arrayOf(
            "create your note..",
            "save your note..",
            "update your note..",
            "delete your note.."

        )
        var i = 1

        for (i in 1..6) {

            helpTextGenerator((i * 1000).toLong(), textArray[i-1])
        }
        Timer().schedule(timerTask {

            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignUpFragment())

        }, 5000)

    }


    private fun helpTextGenerator(delayTime: Long, helpText: String) {

        Timer().schedule(timerTask {

            Dispatchers.IO.run {

                binding.helpText.text = helpText

            }
        }, delayTime)

    }
}