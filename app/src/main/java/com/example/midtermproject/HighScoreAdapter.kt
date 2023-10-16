package com.example.midtermproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HighScoreAdapter(private val onDeleteClickListener: (Score) -> Unit) :
    RecyclerView.Adapter<HighScoreAdapter.ViewHolder>() {

    private var scores = emptyList<Score>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerNameTextView: TextView = itemView.findViewById(R.id.playerNameTextView)
        val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_highscore, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = scores.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val score = scores[position]
        holder.playerNameTextView.text = score.playerName
        holder.scoreTextView.text = score.score.toString()
        holder.deleteButton.setOnClickListener { onDeleteClickListener(score) }
    }

    fun setScores(scores: List<Score>) {
        this.scores = scores
        notifyDataSetChanged()
    }
}
