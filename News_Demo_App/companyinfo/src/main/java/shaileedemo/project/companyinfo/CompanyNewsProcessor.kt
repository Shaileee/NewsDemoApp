package shaileedemo.project.companyinfo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.transform
import shaileedemo.project.common.base.ErrorHolder
import shaileedemo.project.common.ext.DaysWeek
import shaileedemo.project.common.ext.getDate
import shaileedemo.project.common.mvibase.MviProcessor
import shaileedemo.project.companyinfo.archmodel.NewsAction
import shaileedemo.project.companyinfo.archmodel.NewsResult
import shaileedemo.project.companyinfo.mappers.toUiModel
import shaileedemo.project.domain.models.ApiResult
import shaileedemo.project.domain.usecases.GetCompaniesNewsUseCase


class CompanyNewsProcessor(
    private val getCompaniesNewsUseCase: GetCompaniesNewsUseCase
) : MviProcessor<NewsAction, NewsResult> {

    private fun fetchCompanyNews(actions: Flow<NewsAction.FetchNextCompanyNews>): Flow<NewsResult> =
        actions.transform<NewsAction.FetchNextCompanyNews, NewsResult> { action ->
            when (val result = getCompaniesNewsUseCase(
                companyReq = action.companyName,
                dateFrom = DaysWeek.Yesterday.getDate(),
                dateTo = DaysWeek.Today.getDate(),
                pageNews = action.page
            )) {
                is ApiResult.Success -> {
                    result.data?.let {
                        emit(NewsResult.NewsList(it.toUiModel()))
                    } ?: kotlin.run {
                        emit(NewsResult.Error(ErrorHolder.Message("error unknown")))
                    }
                }

                is ApiResult.Error -> {
                    emit(NewsResult.Error(ErrorHolder.Message(result.e.message ?: "")))
                }
            }
        }

    override fun actionProcessor(actions: Flow<NewsAction>): Flow<NewsResult> =
        merge(
            actions.filterIsInstance<NewsAction.FetchNextCompanyNews>().let(::fetchCompanyNews)
        )

}