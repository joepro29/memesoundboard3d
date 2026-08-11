package com.memesoundboard3d.app

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SoundAdapter(
    private val onSoundClick: (Sound, Int) -> Unit
) : ListAdapter<Sound, SoundAdapter.SoundViewHolder>(SoundDiffCallback()) {

    private var currentlyPlayingPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent, false)
        return SoundViewHolder(view)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        val sound = getItem(position)
        holder.bind(sound, position == currentlyPlayingPosition)
        
        holder.itemView.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val previousPosition = currentlyPlayingPosition
                currentlyPlayingPosition = adapterPosition
                
                if (previousPosition != -1) {
                    notifyItemChanged(previousPosition)
                }
                notifyItemChanged(adapterPosition)
                
                animateClick(it)
                onSoundClick(sound, adapterPosition)
            }
        }
    }

    fun setPlayingPosition(position: Int) {
        val previousPosition = currentlyPlayingPosition
        currentlyPlayingPosition = position
        
        if (previousPosition != -1) {
            notifyItemChanged(previousPosition)
        }
        if (position != -1) {
            notifyItemChanged(position)
        }
    }

    private fun animateClick(view: View) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f, 1f)
        
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            duration = 200
            interpolator = OvershootInterpolator()
            start()
        }
    }

    class SoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconText: TextView = itemView.findViewById(R.id.soundIcon)
        private val nameText: TextView = itemView.findViewById(R.id.soundName)
        private val cardView: CardView = itemView.findViewById(R.id.cardView)

        fun bind(sound: Sound, isPlaying: Boolean) {
            iconText.text = sound.icon
            nameText.text = sound.name
            
            if (isPlaying) {
                cardView.setCardBackgroundColor(Color.parseColor("#1B5E20"))
                cardView.strokeColor = Color.parseColor("#00FF88")
                cardView.cardElevation = 16f
                
                itemView.animate()
                    .scaleX(1.05f)
                    .scaleY(1.05f)
                    .setDuration(200)
                    .start()
            } else {
                cardView.setCardBackgroundColor(Color.parseColor("#1A1A2E"))
                cardView.strokeColor = Color.parseColor("#FF0061")
                cardView.cardElevation = 8f
                
                itemView.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(200)
                    .start()
            }
        }
    }

    class SoundDiffCallback : DiffUtil.ItemCallback<Sound>() {
        override fun areItemsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem == newItem
        }
    }
}
