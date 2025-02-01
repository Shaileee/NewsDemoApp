package shaileedemo.project.companyinfo

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import shaileedemo.project.common.base.BaseViewModel
import shaileedemo.project.common.mvibase.MviProcessor
import shaileedemo.project.companyinfo.archmodel.NewsAction
import shaileedemo.project.companyinfo.archmodel.NewsIntent
import shaileedemo.project.companyinfo.archmodel.NewsResult
import shaileedemo.project.companyinfo.archmodel.NewsState

class CompanyNewsViewModel(
    private val processor: MviProcessor<NewsAction, NewsResult>
) : BaseViewModel<
        NewsIntent,
        NewsState>() {

    val companyQueue = ArrayDeque(listOf("Microsoft", "Apple", "Google", "Tesla"))

    var companyName = ""
    var page = 1

    val viewIntents: MutableSharedFlow<NewsIntent> = MutableSharedFlow()
    private var sharedFlow: MutableSharedFlow<NewsIntent> = MutableSharedFlow<NewsIntent>()
    override val viewState: StateFlow<NewsState> = compose()
    val viewStates: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.idle())

    private fun Flow<NewsIntent>.intentFilter(): Flow<NewsIntent> =
        merge(
            filterIsInstance<NewsIntent.InitialIntent>()
                .take(1),
            filterIsInstance<NewsIntent.GetNextCompanyNews>().takeWhile { companyQueue.isNotEmpty() }
        )


    private fun actionFromIntent(intent: NewsIntent): NewsAction {
        return when (intent) {
            is NewsIntent.InitialIntent -> {
                companyName = companyQueue.removeFirst()
                NewsAction.FetchNextCompanyNews(
                    companyName = companyName,
                    page = page
                )
            }

            is NewsIntent.GetNextCompanyNews -> {
                companyName = companyQueue.removeFirst()
                NewsAction.FetchNextCompanyNews(
                    companyName = companyName,
                    page = page
                )
            }
        }
    }

    override suspend fun processIntent(intent: NewsIntent) {
        sharedFlow.emit(intent)
    }

    private fun compose(): StateFlow<NewsState> {
        return sharedFlow.intentFilter()
            .map(::actionFromIntent)
            .let(processor::actionProcessor)
            .scan(NewsState.idle(), ::reducer)
            .distinctUntilChanged()
            .stateIn<NewsState>(viewModelScope, SharingStarted.Eagerly, NewsState.idle())
    }

    private fun reducer(previousState: NewsState, result: NewsResult): NewsState {
        return when (result) {
            is NewsResult.Loading -> {
                previousState.copy(
                    loading = true,
                    error = null
                )
            }

            is NewsResult.Error -> {
                previousState.copy(
                    error = result.error,
                    loading = false
                )
            }

            is NewsResult.NewsList -> {
                val updatedArticles = previousState.companyNewsUiModel?.articles.orEmpty() +
                        result.companyNewsUiModel.articles

                val updatedCompanyNewsUiModel = result.companyNewsUiModel.copy(
                    articles = updatedArticles
                )

                previousState.copy(
                    companyNewsUiModel = updatedCompanyNewsUiModel,
                    loading = false
                )
            }
        }
    }
}