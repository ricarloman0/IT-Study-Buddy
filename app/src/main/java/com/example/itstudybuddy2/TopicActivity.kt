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

        val favoriteButton =
            findViewById<Button>(R.id.favoriteButton)

        val copyButton =
            findViewById<Button>(R.id.copyButton)

        topicTitle.text = title
        topicDescription.text = description
        codeText.text = code

        var favorite = false

        favoriteButton.setOnClickListener {

            favorite = !favorite

            if (favorite) {

                favoriteButton.text =
                    "★ Remove from Favorites"

                Toast.makeText(
                    this,
                    "Added to Favorites",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                favoriteButton.text =
                    "☆ Add to Favorites"

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
}