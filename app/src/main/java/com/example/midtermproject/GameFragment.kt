package com.example.midtermproject

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.databinding.FragmentGameBinding
import com.example.midtermproject.databinding.FragmentGameBottomBinding
import com.example.midtermproject.databinding.FragmentGameTopBinding


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root

        val app = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(app).scoreDao()

        //create MAIN viewModel
        val viewModelFactory = GameViewModelFactory(dao) // Create a ViewModel factory
        val viewModel = ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]

        binding.lifecycleOwner = this

        //make toasts on change of message
        viewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.clearToastMessage()
            }
        })

        //play incorrect sound on wrong guess
        viewModel.incorrectSound.observe(viewLifecycleOwner, Observer { sound ->
            if (sound == true) {
                val mediaPlayer = MediaPlayer.create(context, R.raw.buzz)
                mediaPlayer.setOnCompletionListener { mp ->
                    mp.release() //ensure releases when over
                }
                mediaPlayer.start()

                viewModel.resetIncorrect()
            }
        })
        //game over
        viewModel.isGameOver.observe(viewLifecycleOwner, Observer {status ->
            if (status) {

                val mediaPlayer = MediaPlayer.create(context, R.raw.correct) //play correct sound
                mediaPlayer.setOnCompletionListener { mp ->
                    mp.release() //ensure releases when over
                }
                mediaPlayer.start()

                //pass playerName, score back.
                val action = GameFragmentDirections.actionGameFragmentToMenuFragment( viewModel.attempts.value.toString(), viewModel.name)
                this.findNavController().navigate(action) //navigate back w/ args
            }
        })

        return view
    }

}