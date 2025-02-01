package shaileedemo.project.domain.repositories

import shaileedemo.project.domain.models.ApiResult
import shaileedemo.project.domain.models.CompanyNewsRepoModel

interface CompanyNewsRepository {
    suspend fun getCompanyNews(
        companyReq : String,
        dateFrom : String,
        dateTo : String,
        pageNews: Int
    ): ApiResult<CompanyNewsRepoModel>
}