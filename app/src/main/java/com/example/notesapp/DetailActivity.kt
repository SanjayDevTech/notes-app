package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.notesapp.data.Repository
import com.example.notesapp.model.Note
import com.example.notesapp.ui.theme.NotesAppTheme
import java.util.Date
import java.util.UUID

class DetailActivity : ComponentActivity() {
    private lateinit var note: Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val editMode = intent.getBooleanExtra("editMode", false)
        val noteId = intent.getStringExtra("id") ?: return finish()
        note = if (editMode) {
            Repository.getNote(noteId) ?: return finish()
        } else {
            Note(
                id = noteId,
                title = "",
                description = "",
                createdOn = Date().time,
                emoji = "😎",
                color = Repository.colors.random(),
            )
        }
        if (!editMode) {
            Repository.put(note)
        }
        setContent {
            NotesAppTheme {
                Surface(
                    color = Color(android.graphics.Color.parseColor(note.color)),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        Text(text = note.title)
                        Text(text = note.description)
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Repository.update(note)
    }
}

