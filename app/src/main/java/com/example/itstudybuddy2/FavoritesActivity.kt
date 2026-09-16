package com.example.itstudybuddy2

import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itstudybuddy2.database.AppDatabase
import com.example.itstudybuddy2.database.Favorite
import com.example.itstudybuddy2.database.FavoriteDao
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView
    private lateinit var sortSwitch: Switch

    private lateinit var favoriteDao: FavoriteDao
    private lateinit var adapter: FavoriteAdapter

    private val preferencesName = "ITStudyBuddySettings"
    private val sortKey = "sortAlphabetically"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorites)

        supportActionBar?.title = "My Favorites"

        recyclerView = findViewById(R.id.favoritesRecyclerView)
        emptyText = findViewById(R.id.emptyFavoritesText)
        sortSwitch = findViewById(R.id.sortSwitch)

        favoriteDao =
            AppDatabase.getDatabase(this).favoriteDao()

        adapter = FavoriteAdapter(
            onEdit = { favorite ->
                showEditNoteDialog(favorite)
            },
            onDelete = { favorite ->
                deleteFavorite(favorite)
            }
        )

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        val savedSort =
            getSharedPreferences(
                preferencesName,
                MODE_PRIVATE
            ).getBoolean(sortKey, false)

        sortSwitch.isChecked = savedSort

        sortSwitch.setOnCheckedChangeListener { _, checked ->

            getSharedPreferences(
                preferencesName,
                MODE_PRIVATE
            )
                .edit()
                .putBoolean(sortKey, checked)
                .apply()

            loadFavorites()
        }

        loadFavorites()
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }

    private fun loadFavorites() {

        lifecycleScope.launch {

            var favorites =
                favoriteDao.getAllFavorites()

            val sortAlphabetically =
                getSharedPreferences(
                    preferencesName,
                    MODE_PRIVATE
                )
                    .getBoolean(sortKey, false)

            if (sortAlphabetically) {

                favorites =
                    favorites.sortedBy {
                        it.topicName.lowercase()
                    }
            }

            adapter.submitList(favorites)

            if (favorites.isEmpty()) {

                recyclerView.visibility =
                    RecyclerView.GONE

                emptyText.visibility =
                    TextView.VISIBLE

            } else {

                recyclerView.visibility =
                    RecyclerView.VISIBLE

                emptyText.visibility =
                    TextView.GONE
            }
        }
    }

    private fun showEditNoteDialog(
        favorite: Favorite
    ) {

        val input = EditText(this)

        input.setText(favorite.note)

        input.hint = "Add a note for this topic"
        input.minLines = 3
        input.maxLines = 5

        val padding =
            (16 * resources.displayMetrics.density)
                .toInt()

        input.setPadding(
            padding,
            padding,
            padding,
            padding
        )

        AlertDialog.Builder(this)
            .setTitle("Edit Favorite Note")
            .setMessage(favorite.topicName)
            .setView(input)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Save") { _, _ ->

                lifecycleScope.launch {

                    val updatedFavorite =
                        favorite.copy(
                            note = input.text
                                .toString()
                                .trim()
                        )

                    favoriteDao.updateFavorite(
                        updatedFavorite
                    )

                    loadFavorites()

                    Toast.makeText(
                        this@FavoritesActivity,
                        "Favorite updated",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .show()
    }

    private fun deleteFavorite(
        favorite: Favorite
    ) {

        lifecycleScope.launch {

            favoriteDao.deleteFavorite(
                favorite
            )

            loadFavorites()

            Toast.makeText(
                this@FavoritesActivity,
                "Favorite deleted",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}