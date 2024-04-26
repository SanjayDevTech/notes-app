package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.data.Repository
import com.example.notesapp.ui.comp.NoteCard
import com.example.notesapp.ui.theme.NotesAppTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                val notes = Repository.getNotes()
                Scaffold { paddingValues ->
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        item {
                            Button(
                                onClick = {
                                    val intent =
                                        Intent(this@MainActivity, DetailActivity::class.java).apply {
                                            putExtra("id", UUID.randomUUID().toString())
                                        }
                                    startActivity(intent)
                                },
                                shape = RoundedCornerShape(16),
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "New")
                            }
                        }
                        items(notes, key = {
                            it.id
                        }) {
                            NoteCard(note = it)
                        }
                    }
                }
            }
        }
    }
}
