package com.example.notesapp.model

import java.util.UUID

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val createdOn: Long,
    val emoji: String,
    val color: String,
)
