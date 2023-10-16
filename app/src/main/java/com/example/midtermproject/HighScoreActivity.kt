package com.example.midtermproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HighScoreActivity : AppCompatActivity() {

    private lateinit var highScoresRecyclerView: RecyclerView
    private lateinit var adapter: HighScoreAdapter  // Assuming you've created a RecyclerView Adapter named HighScoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val scoreDao = database.scoreDao()

        val adapter = HighScoreAdapter { score ->
            // Handle delete button click
            // This will call the DAO's delete method to remove the score from the database
        }
        // Fetch scores from database and set to adapter
        val scores = scoreDao.getAllScores().observe(this, Observer { scores ->
            adapter.setScores(scores)
        })

        setContentView(R.layout.activity_highscore)

        highScoresRecyclerView = findViewById(R.id.highScoresRecyclerView)

        // Set up RecyclerView
        highScoresRecyclerView.layoutManager = LinearLayoutManager(this)
        //this.adapter = HighScoreAdapter  // This adapter should be designed to display scores and handle the delete functionality
        highScoresRecyclerView.adapter = adapter

        // Fetch scores from the database and update the RecyclerView
        fetchScoresFromDatabase()
    }

    private fun fetchScoresFromDatabase() {
        // This method will fetch scores from the Room database and update the RecyclerView
        // You will implement this once you've set up the Room database for scores.
    }
}
