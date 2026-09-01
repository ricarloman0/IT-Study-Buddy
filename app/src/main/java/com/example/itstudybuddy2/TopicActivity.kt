package com.example.itstudybuddy2

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val topicTitle =
            findViewById<TextView>(R.id.topicTitle)

        val explanation =
            findViewById<TextView>(R.id.explanation)

        val codeText =
            findViewById<TextView>(R.id.codeText)

        val favoriteButton =
            findViewById<Button>(R.id.favoriteButton)

        val copyButton =
            findViewById<Button>(R.id.copyButton)

        // Get topic information
        val title =
            intent.getStringExtra("title") ?: "FOR LOOP"

        val description =
            intent.getStringExtra("description")
                ?: "Repeats code a set number of times."

        val code =
            intent.getStringExtra("code")
                ?: "for (var i = 1; i <= 10; i++) {\n    println(i)\n}"

        topicTitle.text = title
        explanation.text = description
        codeText.text = code

        // Favorites
        val prefs = getSharedPreferences(
            "favorites",
            MODE_PRIVATE
        )

        val favoriteKey = title.lowercase()

        var isFavorite =
            prefs.getBoolean(favoriteKey, false)

        favoriteButton.text =
            if (isFavorite)
                "★ Remove from Favorites"
            else
                "☆ Add to Favorites"

        favoriteButton.setOnClickListener {

            isFavorite = !isFavorite

            prefs.edit()
                .putBoolean(favoriteKey, isFavorite)
                .apply()

            favoriteButton.text =
                if (isFavorite)
                    "★ Remove from Favorites"
                else
                    "☆ Add to Favorites"

            Toast.makeText(
                this,
                if (isFavorite)
                    "Added to Favorites"
                else
                    "Removed from Favorites",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Copy code
        copyButton.setOnClickListener {

            val clipboard =
                getSystemService(CLIPBOARD_SERVICE)
                        as ClipboardManager

            val clip =
                ClipData.newPlainText(
                    "code",
                    codeText.text.toString()
                )

            clipboard.setPrimaryClip(clip)

            Toast.makeText(
                this,
                "Code copied!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}