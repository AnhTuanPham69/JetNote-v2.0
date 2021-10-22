package com.example.jetnote.ui.screen


import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnote.data.database.model.NoteDbModel
import com.example.jetnote.domain.model.ColorModel
import com.example.jetnote.domain.model.NoteModel
import com.example.jetnote.routing.Screen
import com.example.jetnote.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import ui.components.*

@Composable
fun NotesScreen(viewModel: MainViewModel) {
    val notes: List<NoteModel> by viewModel
        .notesNotInTrash
        .observeAsState(listOf())
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                scaffoldState = scaffoldState,
                scope = coroutineScope
            )
        },
        drawerContent = {
            AppDrawer(currentScreen = Screen.Notes,
                closeDrawerAction = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        content = {
            NoteList(notes = notes, onNoteCheckedChange = { }, onNoteClick = {})
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }
            ) {
                Text("+")
            }
        },
    )
}

@Composable
private fun NoteList(
    notes: List<NoteModel>,
    onNoteCheckedChange: (NoteModel) -> Unit,
    onNoteClick: (NoteModel) -> Unit,
) {
    val backgroundShape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(4.dp)
    LazyColumn{
        items(notes) {
            item ->
            Row(
                modifier= Modifier
                    .padding(8.dp)
                    .shadow(1.dp, backgroundShape)
                    .fillMaxWidth()
                    .heightIn(min = 64.dp)
                    .background(Color.White, backgroundShape)
            ) {
                ColorItem(color= item.color,onColorSelect = {})
                Column(
                    modifier= Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        item.title,
                        maxLines = 1,
                        color= Color.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 0.15.sp
                        )
                    )
                    Text(
                        item.content,
                        maxLines = 1,
                        color= Color.Black.copy(alpha = 0.75f),
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            letterSpacing = 0.25.sp
                        )
                    )
                }

            }
        }
    }
}

