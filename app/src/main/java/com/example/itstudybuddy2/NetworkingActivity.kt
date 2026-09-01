package com.example.itstudybuddy2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NetworkingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        findViewById<Button>(R.id.ipButton)
            .setOnClickListener {
                openTopic(
                    "IP Address",
                    "An IP address identifies a device on a network.",
                    "192.168.1.10"
                )
            }

        findViewById<Button>(R.id.dnsButton)
            .setOnClickListener {
                openTopic(
                    "DNS",
                    "DNS translates domain names into IP addresses.",
                    "example.com → 93.184.216.34"
                )
            }

        findViewById<Button>(R.id.dhcpButton)
            .setOnClickListener {
                openTopic(
                    "DHCP",
                    "DHCP automatically gives devices network settings.",
                    "Device → DHCP → IP Address"
                )
            }

        findViewById<Button>(R.id.tcpButton)
            .setOnClickListener {
                openTopic(
                    "TCP / UDP",
                    "TCP focuses on reliable delivery while UDP is faster and connectionless.",
                    "TCP: reliable\nUDP: fast"
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