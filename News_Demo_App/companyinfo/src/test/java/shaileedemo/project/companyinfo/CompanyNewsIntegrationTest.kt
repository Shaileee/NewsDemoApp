package shaileedemo.project.companyinfo

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import shaileedemo.project.domain.usecases.GetCompaniesNewsUseCase
import shaileedemo.project.data.modules.remoteDataModule
import shaileedemo.project.domain.models.ApiResult
import kotlin.test.assertTrue

class CompanyNewsIntegrationTest : KoinTest {

    private lateinit var useCase: GetCompaniesNewsUseCase

    @Before
    fun setUp() {
        startKoin {
            modules(remoteDataModule)
        }
        useCase = GetCompaniesNewsUseCase(get())
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should fetch and process company news successfully`(): Unit = runBlocking {
        val result = useCase(
            companyReq = "microsoft",
            dateFrom = "2025-01-13",
            dateTo = "2025-01-15",
            pageNews = 1
        )
        println("Result type: ${result::class.simpleName}")
        println("Result data: ${result}")
        assertTrue(result is ApiResult.Success, "Result is not of type CompanyNewsRepoModel")
    }
}
