package com.apptikar.feature.chat.presentation.state

sealed interface SwipeState{
 object  Expanded: SwipeState
 object  Collapsed : SwipeState
}