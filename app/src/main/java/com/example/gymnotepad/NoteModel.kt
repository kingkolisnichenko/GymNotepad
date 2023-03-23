package com.example.gymnotepad

import java.util.UUID

data class NoteModel(
    val id: UUID = UUID.randomUUID(),
    val Name: String = "",
    val Weight: Int = 0,
    val Count: Int = 0
): java.io.Serializable
