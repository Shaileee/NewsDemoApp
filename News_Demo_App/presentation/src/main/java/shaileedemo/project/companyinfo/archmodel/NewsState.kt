package shaileedemo.project.companyinfo.archmodel

import shaileedemo.project.common.base.ErrorHolder
import shaileedemo.project.common.mvibase.MviState
import shaileedemo.project.companyinfo.models.CompanyNewsUiModel

data class NewsState(
    val companyNewsUiModel: CompanyNewsUiModel?,
    val loading: Boolean,
    val error: ErrorHolder?
) : MviState {
    companion object {
        fun idle(): NewsState {
            return NewsState(
                companyNewsUiModel = null,
                loading = false,
                error = null,
            )
        }
    }
}