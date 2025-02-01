package shaileedemo.project.common.base

import shaileedemo.project.common.mvibase.MviIntent
import shaileedemo.project.common.mvibase.MviState
import kotlinx.coroutines.flow.Flow

interface MviView<out I : MviIntent, in S : MviState> {
    fun intents(): Flow<I>
    fun render(state: S)
}
