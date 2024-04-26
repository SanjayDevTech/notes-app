package com.example.notesapp.data

import androidx.compose.runtime.mutableStateListOf
import com.example.notesapp.model.Note
import kotlinx.coroutines.flow.flowOf
import java.util.Calendar

object Repository {
    val colors = listOf(
        "#e87676",
        "#e8af76",
        "#e876af",
        "#76e878",
        "#76c4e8",
        "#be76e8",
        "#e8da76"
    )
    private val notes = mutableStateListOf(
        Note(
            title = "Title Small",
            description = """
                            Long description so that we can test elipsis, but this is going to be the longest sentences because I want it to be to show elipsis.
                            
                            I read that book, sorry I cannot read it.
                            
                            Hi This is a multiline
                        """.trimIndent(),
            createdOn = Calendar.getInstance().timeInMillis,
            emoji = "😁",
            color = "#e87676"
        ),
        Note(
            title = "Title Really really Long long long long yes its so long that even I cannot type more than that",
            description = """
                            Long description so that we can test elipsis, but this is going to be the longest sentences because I want it to be to show elipsis.
                            
                            I read that book, sorry I cannot read it.
                            
                            Hi This is a multiline
                        """.trimIndent(),
            createdOn = Calendar.getInstance().timeInMillis,
            emoji = "😜",
            color = "#e8af76"
        )
    )
    fun getNotes(): List<Note> = notes
    fun update(note: Note) {
        val index = notes.indexOfFirst { it.id == note.id }
        notes[index] = note
    }
    fun put(note: Note) {
        notes.add(note)
    }
    fun getNote(id: String): Note? {
        return notes.find { it.id == id }
    }
}
