package shaileedemo.project.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*
import shaileedemo.project.common.mvibase.MviIntent
import shaileedemo.project.common.mvibase.MviState

abstract class BaseViewModel<I : MviIntent, S : MviState> : ViewModel(){
    abstract val viewState: StateFlow<S>
    abstract suspend fun processIntent(intent: I)
}