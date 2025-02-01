package shaileedemo.project.data.repository

import shaileedemo.project.common.Mapper
import shaileedemo.project.data.models.CompanyNewsServerModel
import shaileedemo.project.data.remote.CompanyNewsRemoteApi
import shaileedemo.project.data.remote.MapRemoteApiServiceToApiResultModel
import shaileedemo.project.domain.models.ApiResult
import shaileedemo.project.domain.models.CompanyNewsRepoModel
import shaileedemo.project.domain.repositories.CompanyNewsRepository

class CompanyNewsRepositoryImpl(
    private val companyNewsRemoteApi: CompanyNewsRemoteApi,
    private val companyNewsMapper: Mapper<CompanyNewsServerModel, CompanyNewsRepoModel>
) : CompanyNewsRepository {
    override suspend fun getCompanyNews(
        companyReq: String,
        dateFrom: String,
        dateTo: String,
        pageNews: Int
    ): ApiResult<CompanyNewsRepoModel> =
        MapRemoteApiServiceToApiResultModel(companyNewsMapper).map(
            companyNewsRemoteApi.getCompanyNews(
                company = companyReq,
                from = dateFrom,
                to = dateTo,
                page = pageNews
            )
        )
}