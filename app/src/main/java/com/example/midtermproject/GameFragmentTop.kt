package com.example.midtermproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.midtermproject.databinding.FragmentGameTopBinding
import com.example.midtermproject.GameViewModel


class GameFragmentTop : Fragment() {
    private var _binding: FragmentGameTopBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameTopBinding.inflate(inflater, container, false)
        val view = binding.root

        //Shared VM Connection
        val app = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(app).scoreDao()
        val viewModelFactory = GameViewModelFactory(dao)
        viewModel = ViewModelProvider(requireParentFragment(), viewModelFactory)[GameViewModel::class.java]

        binding.viewModel = viewModel

        //Submit Button
        binding.okButton.setOnClickListener {
            //log number in Logcat for testing purposes.
            Log.d("number",viewModel.randomNumber.toString())
            viewModel.name = binding.playerNameInput.text.toString()
            viewModel.submitGuess(binding.guessTextInput.text.toString())
        }

        //Minus Button
        binding.minusButton.setOnClickListener {
            val curNum = binding.guessTextInput.text.toString().toIntOrNull()
            if (curNum != null) { //if not empty
                binding.guessTextInput.setText((curNum - 1).toString())
            }
        }
        //Plus Button
        binding.plusButton.setOnClickListener {
            val curNum = binding.guessTextInput.text.toString().toIntOrNull()
            if (curNum != null) { //if not empty
                binding.guessTextInput.setText((curNum + 1).toString())
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}