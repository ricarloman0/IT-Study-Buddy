package com.example.itstudybuddy2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TopicActivity : AppCompatActivity() {

    private lateinit var favoriteButton: Button
    private lateinit var copyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_topic)

        val title =
            intent.getStringExtra("title") ?: "Topic"

        val description =
            intent.getStringExtra("description")
                ?: "No description available."

        val code =
            intent.getStringExtra("code")
                ?: "No code available."

        val topicTitle =
            findViewById<TextView>(R.id.topicTitle)

        val topicDescription =
            findViewById<TextView>(R.id.topicDescription)

        val codeText =
            findViewById<TextView>(R.id.codeText)

        favoriteButton =
            findViewById(R.id.favoriteButton)

        copyButton =
            findViewById(R.id.copyButton)

        topicTitle.text = title
        topicDescription.text = description
        codeText.text = code

        val preferences =
            getSharedPreferences(
                "ITStudyBuddyFavorites",
                MODE_PRIVATE
            )

        var favorite =
            preferences.getBoolean(title, false)

        updateFavoriteButton(favorite)

        favoriteButton.setOnClickListener {

            favorite = !favorite

            preferences.edit()
                .putBoolean(title, favorite)
                .apply()

            updateFavoriteButton(favorite)

            if (favorite) {
                Toast.makeText(
                    this,
                    "Added to Favorites",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Removed from Favorites",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        copyButton.setOnClickListener {

            val clipboard =
                getSystemService(
                    Context.CLIPBOARD_SERVICE
                ) as ClipboardManager

            val clip =
                ClipData.newPlainText(
                    "Code",
                    code
                )

            clipboard.setPrimaryClip(clip)

            Toast.makeText(
                this,
                "Code copied!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateFavoriteButton(
        favorite: Boolean
    ) {
        if (favorite) {
            favoriteButton.text =
                "★ Remove from Favorites"
        } else {
            favoriteButton.text =
                "☆ Add to Favorites"
        }
    }
}