package com.example.gymnotepad

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.example.gymnotepad.ui.theme.GymNotepadTheme

class NoteActivity : ComponentActivity() {

    private val mNote by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra(NOTE_ID, NoteModel::class.java)
        } else {
            intent?.getSerializableExtra(NOTE_ID) as NoteModel
        } ?: NoteModel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GymNotepadTheme {

                Text(text = mNote.Name)

            }
        }
    }

    companion object {
        private const val NOTE_ID = "NOTE_ID"
        fun newIntent(context: Context, note: NoteModel) =
            Intent(context, NoteActivity::class.java).apply {
                putExtra(NOTE_ID, note)
            }
    }


}