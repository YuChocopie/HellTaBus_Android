package com.mashup.healthyup.features.launcher

import androidx.lifecycle.viewModelScope
import com.mashup.healthyup.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor() : BaseViewModel() {

    sealed class Action {
        object StartLogin : Action()
    }

    private val action = Channel<Action>(Channel.BUFFERED)
    val actionFlow: Flow<Action>
        get() = action.receiveAsFlow()

    init {
        // TODO : 로그인 유무 체크, 로그인 되어 있으면, 홈화면으로 이동, 로그인 안되어 있으면, 로그인 화면으로 이동

        viewModelScope.launch {
            delay(200L)
            action.trySend(Action.StartLogin)
        }
    }
}