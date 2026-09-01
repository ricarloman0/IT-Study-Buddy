package com.example.itstudybuddy2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TroubleshootingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_troubleshooting)

        val internetButton =
            findViewById<Button>(R.id.internetButton)

        val slowPcButton =
            findViewById<Button>(R.id.slowPcButton)

        val usbButton =
            findViewById<Button>(R.id.usbButton)

        internetButton.setOnClickListener {
            openTopic(
                "No Internet",
                "Check Wi-Fi, cables, airplane mode, and restart the router.",
                "1. Check Wi-Fi\n2. Restart router\n3. Test connection"
            )
        }

        slowPcButton.setOnClickListener {
            openTopic(
                "Slow PC",
                "Close unnecessary programs and check available storage.",
                "1. Close apps\n2. Check storage\n3. Restart PC"
            )
        }

        usbButton.setOnClickListener {
            openTopic(
                "USB Not Detected",
                "Try another USB port or reconnect the device.",
                "1. Reconnect USB\n2. Try another port\n3. Restart"
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