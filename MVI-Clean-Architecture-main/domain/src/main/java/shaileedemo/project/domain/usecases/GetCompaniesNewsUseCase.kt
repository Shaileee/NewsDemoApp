package shaileedemo.project.domain.usecases

import shaileedemo.project.domain.models.ApiResult
import shaileedemo.project.domain.models.CompanyNewsRepoModel
import shaileedemo.project.domain.repositories.CompanyNewsRepository

class GetCompaniesNewsUseCase(private val companyNewsRepository: CompanyNewsRepository) {
    suspend operator fun invoke(
        companyReq: String,
        dateFrom: String,
        dateTo: String,
        pageNews: Int
    ): ApiResult<CompanyNewsRepoModel> = companyNewsRepository.getCompanyNews(
        companyReq = companyReq,
        dateFrom = dateFrom,
        dateTo = dateTo,
        pageNews = pageNews
    )
}