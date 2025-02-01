package shaileedemo.project.companyinfo


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import shaileedemo.project.common.base.BaseActivity
import shaileedemo.project.companyinfo.archmodel.NewsIntent
import shaileedemo.project.companyinfo.archmodel.NewsState
import shaileedemo.project.companyinfo.ui.theme.CompanyNewsNavHost

class MainActivity : BaseActivity<
        NewsIntent,
        NewsState,
        CompanyNewsViewModel>(
) {

    override val viewModel: CompanyNewsViewModel by viewModel()
    override fun setupViews() {
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CompanyNewsNavHost(viewModel)
                }
            }
        }
    }

    override fun intents(): Flow<NewsIntent> =
        merge(
            flowOf(NewsIntent.InitialIntent),
            viewModel.viewIntents
        )

    override fun render(state: NewsState) {
        viewModel.viewModelScope.launch {
            viewModel.viewStates.emit(state)
        }
    }
}