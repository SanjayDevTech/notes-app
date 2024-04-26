package com.example.notesapp.ui.comp

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.DetailActivity
import com.example.notesapp.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(note: Note) {
    val context = LocalContext.current
    val title = note.title.replace("\n", " ")
    val description = note.description.replace("\n", " ")
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(note.color))
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(15.dp),
        onClick = {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("id", note.id)
                putExtra("editMode", true)
            }
            context.startActivity(intent)
        }
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(0.9f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W500
                )
                Box(
                    modifier = Modifier
                        .weight(0.1f, fill = false)
                        .drawBehind {
                            drawCircle(Color.Black, style = Stroke(4f))
                        }
                ) {
                    Text(text = note.emoji)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = description,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
