package com.bojanvilic.film.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(): ViewModel() {

    var progressBarValue by mutableStateOf(0f)

    init {
        Handler(Looper.getMainLooper()).postDelayed({
            progressBarValue = 1f
        }, 500)
    }
}