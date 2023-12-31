package com.example.midtermproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ScoresViewModel (val dao: ScoreDao) : ViewModel() {
    //Scores
    val scores: LiveData<List<Score>> = dao.getAllScores()

    //Navigation Boolean
    private val _navigateToMenu = MutableLiveData<Boolean>(false)
    val navigateToMenu: LiveData<Boolean>
        get() = _navigateToMenu

    //Delete score button
    fun deleteScore (scoreId: Long) {
        viewModelScope.launch {
            val scoreToDelete = Score(scoreId = scoreId) // find requested score w/ scoreId
            dao.delete(scoreToDelete)
        }
    }


    fun backToMenu() {
        _navigateToMenu.value = true
    }


    fun onMenuNavigated() { //Navigation Protection
        _navigateToMenu.value = false
    }

    // logging function to print all scores to Logcat (debug)
    fun logAllScores() {
        val allScores = scores.value
        allScores?.forEach { score ->
            Log.d("ScoresViewModel", "score player: ${score.playerName}, score: ${score.playerScore}")
        }
    }
}