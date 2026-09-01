package com.example.itstudybuddy2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(
            R.id.programmingButton
        ).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ProgrammingActivity::class.java
                )
            )
        }

        findViewById<Button>(
            R.id.htmlButton
        ).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    HTMLActivity::class.java
                )
            )
        }

        findViewById<Button>(
            R.id.sqlButton
        ).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SQLActivity::class.java
                )
            )
        }

        findViewById<Button>(
            R.id.networkingButton
        ).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    NetworkingActivity::class.java
                )
            )
        }

        findViewById<Button>(
            R.id.troubleshootingButton
        ).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    TroubleshootingActivity::class.java
                )
            )
        }
    }
}