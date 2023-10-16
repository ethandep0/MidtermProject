package com.example.midtermproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.midtermproject.viewmodel.GameViewModel

class GuessFragment : Fragment(R.layout.fragment_guess) {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decrementButton: ImageButton = view.findViewById(R.id.decrementButton)
        val incrementButton: ImageButton = view.findViewById(R.id.incrementButton)
        val guessEditText: EditText = view.findViewById(R.id.guessEditText)
        val submitGuessButton: Button = view.findViewById(R.id.submitGuessButton)

        decrementButton.setOnClickListener {
            val currentGuess = guessEditText.text.toString().toIntOrNull() ?: 0
            guessEditText.setText((currentGuess - 1).toString())
        }

        incrementButton.setOnClickListener {
            val currentGuess = guessEditText.text.toString().toIntOrNull() ?: 0
            guessEditText.setText((currentGuess + 1).toString())
        }

        submitGuessButton.setOnClickListener {
            val userGuess = guessEditText.text.toString().toIntOrNull()
            if (userGuess != null) {
                val feedback = viewModel.checkGuess(userGuess)
                Toast.makeText(context, feedback, Toast.LENGTH_SHORT).show()

                // If the user's guess is correct, handle game win scenario
                if (feedback == "Correct!") {
                    // Store score and name in the database and navigate back to MainActivity
                    // This can be handled with another function or directly here.
                }
            }
        }
    }
}

