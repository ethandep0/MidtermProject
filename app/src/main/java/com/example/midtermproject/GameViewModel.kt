package com.example.midtermproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel (val dao: ScoreDao) : ViewModel() {
    var randomNumber: Int = (1..100).random()
    var name: String = ""

    //sentry for ending games
    private val _gameOver = MutableLiveData<Boolean>()
    val isGameOver: LiveData<Boolean>
        get() = _gameOver
    //track attempts
    private val _attempts = MutableLiveData<Int>()
    val attempts: LiveData<Int>
        get() = _attempts

    init {
        _attempts.value = 0
    }

    //incrementing attempts
    fun incrementAttempts() {
        val currentAttempts = _attempts.value ?: 0
        _attempts.value = currentAttempts + 1
    }

    //Passes correct message to the main fragment
    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?>
        get() = _toastMessage

    init {
        _toastMessage.value = null
    }

    //Sets toast message
    fun setToastMessage(message: String) {
        _toastMessage.postValue(message)
    }

    //Clears message for next
    fun clearToastMessage() {
        _toastMessage.value = null
    }

    //Playing and resetting the incorrect sounds
    private val _incorrectSound = MutableLiveData<Boolean?>()
    val incorrectSound: LiveData<Boolean?>
        get() = _incorrectSound

    fun playIncorrect(){
        _incorrectSound.value = true
    }
    fun resetIncorrect() {
        _incorrectSound.value = false
    }

    //main game logic
    fun submitGuess(guess: String) {
        viewModelScope.launch {
            if (guess.isNotEmpty()) {

                val guessedNumber = guess.toInt() //convert to integer

                incrementAttempts() // increment attempt (valid attempt found)

                when {
                    guessedNumber == randomNumber -> {
                        //Correct
                        setToastMessage("Congratulations! You guessed it right!")

                        //If no name
                        if (name.isEmpty()) {name = "Unnamed"}

                        //Show player on highscores
                        dao.insert(Score(playerName = name, playerScore = _attempts.value.toString()))

                        _gameOver.value = true
                    }

                    guessedNumber < randomNumber -> {
                        //Lower
                        playIncorrect()
                        setToastMessage("Your guess is too low. Try a higher number.")
                    }

                    else -> {
                        //Higher
                        playIncorrect()
                        setToastMessage("Your guess is too high. Try a lower number.")
                    }
                }
            } else {
                //No guess
                setToastMessage("Please enter a guess.")
            }
        }
    }
}
