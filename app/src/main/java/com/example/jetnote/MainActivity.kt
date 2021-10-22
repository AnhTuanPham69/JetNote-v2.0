package com.example.jetnote

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import com.example.jetnote.routing.Screen
import com.example.jetnote.theme.JetNotesTheme
import com.example.jetnote.ui.screen.NotesScreen


import com.example.jetnote.viewmodel.MainViewModel
import com.example.jetnote.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch
import ui.components.AppDrawer
import ui.components.TopAppBar
import ui.components.Notes


/**
 * Main activity for the app.
 */

class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels(factoryProducer = {
    MainViewModelFactory(
      this,
      (application as JetNotesApplication).dependencyInjector.repository
    )
  })

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent{
      NotesScreen(viewModel=viewModel)
    }

  }
}
