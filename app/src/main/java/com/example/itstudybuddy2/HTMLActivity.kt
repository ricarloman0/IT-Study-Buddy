package com.example.itstudybuddy2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class HTMLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_htmlactivity)

        val searchBox =
            findViewById<EditText>(R.id.searchBox)

        val basicTagsButton =
            findViewById<Button>(R.id.basicTagsButton)

        val selectorsButton =
            findViewById<Button>(R.id.selectorsButton)

        val flexboxButton =
            findViewById<Button>(R.id.flexboxButton)

        searchBox.addTextChangedListener(
            object : TextWatcher {

                override fun afterTextChanged(
                    s: Editable?
                ) {
                    val search =
                        s.toString().trim().lowercase()

                    basicTagsButton.visibility =
                        if (
                            search.isEmpty() ||
                            "basic html tags".contains(search) ||
                            "html".contains(search) ||
                            "tags".contains(search)
                        ) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                    selectorsButton.visibility =
                        if (
                            search.isEmpty() ||
                            "css selectors".contains(search) ||
                            "css".contains(search) ||
                            "selectors".contains(search)
                        ) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                    flexboxButton.visibility =
                        if (
                            search.isEmpty() ||
                            "flexbox".contains(search)
                        ) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                }
            }
        )

        basicTagsButton.setOnClickListener {
            openTopic(
                "Basic HTML Tags",
                "HTML tags create the structure of a webpage.",
                "<h1>Hello World</h1>"
            )
        }

        selectorsButton.setOnClickListener {
            openTopic(
                "CSS Selectors",
                "Selectors choose which HTML elements CSS will style.",
                "p {\n    color: blue;\n}"
            )
        }

        flexboxButton.setOnClickListener {
            openTopic(
                "Flexbox",
                "Flexbox helps arrange elements in rows or columns.",
                "display: flex;\njustify-content: center;"
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