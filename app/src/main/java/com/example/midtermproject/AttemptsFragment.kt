package com.example.midtermproject

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.midtermproject.viewmodel.GameViewModel

class AttemptsFragment : Fragment(R.layout.fragment_attempts) {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val attemptsTextView: TextView = view.findViewById(R.id.attemptsTextView)

        // Observe the number of attempts from the ViewModel and update the UI
        viewModel.attempts.observe(viewLifecycleOwner) { attempts ->
            attemptsTextView.text = "Number of attempts: $attempts"
        }
    }
}

