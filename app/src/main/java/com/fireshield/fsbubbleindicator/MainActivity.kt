package com.fireshield.fsbubbleindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fireshield.library.FSBubbleIndicator

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

//    findViewById<FSBubbleIndicator>(R.id.bubble).count = 777
  }
}
