package com.example.midtermproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.guessFragmentContainer, GuessFragment())
                .replace(R.id.attemptsFragmentContainer, AttemptsFragment())
                .commit()
        }
    }
    fun handleGameEnd(playerName: String, attempts: Int) {
        // Store the result in the database (this will require setting up the database first)

        val intent = Intent().apply {
            putExtra("PLAYER_NAME", playerName)
            putExtra("ATTEMPTS", attempts)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}
