package com.example.midtermproject

import androidx.recyclerview.widget.DiffUtil

class ScoreDiffUtil : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score) //Update recycler and notes.
            = (oldItem.scoreId == newItem.scoreId)

    override fun areContentsTheSame(oldItem: Score, newItem: Score) //No changes to recycler
            = (oldItem == newItem)
}