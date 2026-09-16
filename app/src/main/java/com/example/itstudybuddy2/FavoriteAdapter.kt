package com.example.itstudybuddy2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itstudybuddy2.database.Favorite

class FavoriteAdapter(
    private val onEdit: (Favorite) -> Unit,
    private val onDelete: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var favorites: List<Favorite> = emptyList()

    fun submitList(newFavorites: List<Favorite>) {

        favorites = newFavorites

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_favorite,
                    parent,
                    false
                )

        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FavoriteViewHolder,
        position: Int
    ) {

        val favorite =
            favorites[position]

        holder.topicName.text =
            favorite.topicName

        if (favorite.note.isBlank()) {

            holder.note.text =
                "No note added yet."

        } else {

            holder.note.text =
                "Note: ${favorite.note}"
        }

        holder.editButton.setOnClickListener {
            onEdit(favorite)
        }

        holder.deleteButton.setOnClickListener {
            onDelete(favorite)
        }
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    class FavoriteViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        val topicName: TextView =
            view.findViewById(R.id.favoriteTopicName)

        val note: TextView =
            view.findViewById(R.id.favoriteNote)

        val editButton: Button =
            view.findViewById(R.id.editFavoriteButton)

        val deleteButton: Button =
            view.findViewById(R.id.deleteFavoriteButton)
    }
}