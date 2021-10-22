package com.example.jetnote.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.jetnote.data.repository.Repository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
  owner: SavedStateRegistryOwner,
  private val repository: Repository,
  private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

  override fun <T : ViewModel?> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ): T {
    return MainViewModel(repository) as T
  }
}
