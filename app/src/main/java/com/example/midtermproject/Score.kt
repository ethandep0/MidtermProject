package com.example.midtermproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class Score (
    @PrimaryKey(autoGenerate = true) var scoreId: Long = 0L,
    @ColumnInfo(name = "name") var playerName: String = "",
    @ColumnInfo(name = "score") var playerScore: String = ""
)

