package shaileedemo.project.companyinfo.archmodel

import shaileedemo.project.common.mvibase.MviAction

sealed class NewsAction : MviAction {
    data class FetchNextCompanyNews(
        val companyName: String,
        val page: Int
    ) : NewsAction()
}