package com.example.jetnote

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

/**
 * Splash Screen with the app icon and name at the center.
 *
 * This is also the launch screen.
 *
 * It will open the [MainActivity] after certain delay.
 */
class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_splash)

    showMainActivityWithDelay()
  }

  private fun showMainActivityWithDelay() {
    // Using a handler to delay loading the MainActivity
    Handler(Looper.getMainLooper()).postDelayed({

      // Start activity
      startActivity(Intent(this, MainActivity::class.java))

      // Animate the loading of new activity
      overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

      // Close this activity
      finish()

    }, 2000)
  }
}
