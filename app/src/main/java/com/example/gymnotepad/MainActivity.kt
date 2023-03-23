package com.example.gymnotepad

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.LayoutDirection
import com.example.gymnotepad.ui.theme.GymNotepadTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = MainViewModel()

        setContent {
            GymNotepadTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NotesView(vm){
                        startActivity(NoteActivity.newIntent(this, it))
                    }
                }
            }
        }
    }

    @Composable
    private fun NotesView(vm: MainViewModel, navigateToNote:(NoteModel) -> Unit) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Gym Notes") }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        vm.addNote(NoteModel(UUID.randomUUID(), "Test"))
                    },
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add FAB",
                        tint = androidx.compose.ui.graphics.Color.White,
                    )
                }
            }
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = contentPadding.calculateBottomPadding())
            ) {
                items(vm.notes) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .fillMaxSize(),
                        elevation = 2.dp,
                        backgroundColor = androidx.compose.ui.graphics.Color.White,
                        shape = RoundedCornerShape(corner = CornerSize(16.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable{navigateToNote(it)}
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize()
                                    .align(Alignment.CenterVertically)
                            ) {
                                Text(text = it.Name, style = typography.h6)
                                Text(
                                    text = it.Weight.toString(), style = typography.caption
                                )
                            }
                        }
                    }

                }
            }
        }

    }
}
