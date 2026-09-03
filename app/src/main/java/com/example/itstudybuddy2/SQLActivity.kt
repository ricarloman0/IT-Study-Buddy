package com.example.itstudybuddy2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SQLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sqlactivity)

        findViewById<Button>(R.id.selectButton)
            .setOnClickListener {
                openTopic(
                    "SQL SELECT",
                    "SELECT retrieves data from a database table.",
                    "SELECT * FROM students;"
                )
            }

        findViewById<Button>(R.id.insertButton)
            .setOnClickListener {
                openTopic(
                    "SQL INSERT",
                    "INSERT adds a new record to a table.",
                    "INSERT INTO students VALUES (1, 'John');"
                )
            }

        findViewById<Button>(R.id.updateButton)
            .setOnClickListener {
                openTopic(
                    "SQL UPDATE",
                    "UPDATE changes existing records.",
                    "UPDATE students SET name = 'John' WHERE id = 1;"
                )
            }

        findViewById<Button>(R.id.deleteButton)
            .setOnClickListener {
                openTopic(
                    "SQL DELETE",
                    "DELETE removes records from a table.",
                    "DELETE FROM students WHERE id = 1;"
                )
            }
    }

    private fun openTopic(
        title: String,
        description: String,
        code: String
    ) {
        val intent =
            Intent(this, TopicActivity::class.java)

        intent.putExtra("title", title)
        intent.putExtra("description", description)
        intent.putExtra("code", code)

        startActivity(intent)
    }
}