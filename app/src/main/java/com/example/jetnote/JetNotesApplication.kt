package com.example.jetnote

import android.app.Application
import com.example.jetnote.dependencyinjection.DependencyInjector

/**
 * Application class responsible for initializing the dependency injector.
 */
class JetNotesApplication : Application() {

  lateinit var dependencyInjector: DependencyInjector

  override fun onCreate() {
    super.onCreate()
    initDependencyInjector()
  }

  private fun initDependencyInjector() {
    dependencyInjector = DependencyInjector(this)
  }
}
