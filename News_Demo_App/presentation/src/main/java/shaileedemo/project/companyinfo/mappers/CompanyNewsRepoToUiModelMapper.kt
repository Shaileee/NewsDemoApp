package shaileedemo.project.companyinfo.mappers

import shaileedemo.project.common.Mapper
import shaileedemo.project.companyinfo.models.ArticleUiModel
import shaileedemo.project.companyinfo.models.CompanyNewsUiModel
import shaileedemo.project.companyinfo.models.SourceUiModel
import shaileedemo.project.domain.models.ArticleRepoModel
import shaileedemo.project.domain.models.CompanyNewsRepoModel
import shaileedemo.project.domain.models.SourceRepoModel


object SourceRepoToUiModelMapper : Mapper<SourceRepoModel, SourceUiModel> {
    override fun map(item: SourceRepoModel): SourceUiModel = SourceUiModel(
        id = item.id,
        name = item.name
    )
}

object ArticleRepoToUiModelMapper : Mapper<ArticleRepoModel, ArticleUiModel> {
    override fun map(item: ArticleRepoModel): ArticleUiModel = ArticleUiModel(
        source = item.source?.let { SourceRepoToUiModelMapper.map(it) },
        author = item.author,
        title = item.title,
        description = item.description,
        url = item.url,
        urlToImage = item.urlToImage,
        publishedAt = item.publishedAt,
        content = item.content
    )
}

object CompanyNewsRepoToUiModelMapper : Mapper<CompanyNewsRepoModel, CompanyNewsUiModel> {
    override fun map(item: CompanyNewsRepoModel): CompanyNewsUiModel = CompanyNewsUiModel(
        status = item.status,
        totalResults = item.totalResults,
        articles = item.articles.map { ArticleRepoToUiModelMapper.map(it) }
    )
}

fun CompanyNewsRepoModel.toUiModel(): CompanyNewsUiModel =
    CompanyNewsRepoToUiModelMapper.map(this)
