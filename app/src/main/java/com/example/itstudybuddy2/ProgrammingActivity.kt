package com.example.itstudybuddy2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProgrammingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_programming)

        findViewById<Button>(R.id.variablesButton)
            .setOnClickListener {

                openTopic(
                    "VARIABLES",
                    "Variables store data that can be used by a program.",
                    "var name = \"John\"\nvar age = 20"
                )
            }

        findViewById<Button>(R.id.ifButton)
            .setOnClickListener {

                openTopic(
                    "IF STATEMENTS",
                    "If statements allow a program to make decisions.",
                    "if (age >= 18) {\n    println(\"Adult\")\n}"
                )
            }

        findViewById<Button>(R.id.loopsButton)
            .setOnClickListener {

                openTopic(
                    "FOR LOOP",
                    "Repeats code a set number of times.",
                    "for (var i = 1; i <= 10; i++) {\n    println(i)\n}"
                )
            }

        findViewById<Button>(R.id.functionsButton)
            .setOnClickListener {

                openTopic(
                    "FUNCTIONS",
                    "Functions are reusable blocks of code that perform a task.",
                    "fun greet(name: String) {\n    println(\"Hello \$name\")\n}"
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