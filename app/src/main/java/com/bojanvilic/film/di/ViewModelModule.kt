package com.bojanvilic.film.di

import androidx.lifecycle.SavedStateHandle
import com.bojanvilic.film.ui.ChatViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun getChatViewModel(
        savedStateHandle: SavedStateHandle
    ): ChatViewModel {
        return ChatViewModel(savedStateHandle)
    }
}