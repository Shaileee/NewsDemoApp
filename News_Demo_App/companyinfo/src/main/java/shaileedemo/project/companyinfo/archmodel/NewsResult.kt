package shaileedemo.project.companyinfo.archmodel

import shaileedemo.project.common.base.ErrorHolder
import shaileedemo.project.common.mvibase.MviResult
import shaileedemo.project.companyinfo.models.CompanyNewsUiModel

sealed class NewsResult : MviResult{
    data class NewsList(
        val companyNewsUiModel : CompanyNewsUiModel
    ) : NewsResult()
    data class Error(val error: ErrorHolder) : NewsResult()
    data object Loading : NewsResult()
}