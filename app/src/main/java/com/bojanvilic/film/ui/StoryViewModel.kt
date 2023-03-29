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
    var progressBarValue2 by mutableStateOf(0f)
    var progressBarValue3 by mutableStateOf(0f)

    init {
        Handler(Looper.getMainLooper()).postDelayed({
            progressBarValue = 1f
        }, 500)

        Handler(Looper.getMainLooper()).postDelayed({
            progressBarValue2 = 1f
        }, 8500)

        Handler(Looper.getMainLooper()).postDelayed({
            progressBarValue3 = 1f
        }, 16500)
    }
}