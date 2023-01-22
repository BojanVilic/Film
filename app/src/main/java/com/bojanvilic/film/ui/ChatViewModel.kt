package com.bojanvilic.film.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bojanvilic.film.ui.models.Conversation
import com.bojanvilic.film.ui.navigation.chatId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val chat: Flow<Conversation> =
        savedStateHandle.getStateFlow(chatId, initialValue = "0")
            .flatMapLatest { id ->
                flowOf(conversationList[id.toInt()])
            }
}