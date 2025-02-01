package shaileedemo.project.cleanachitecture


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import shaileedemo.project.common.mvibase.MviProcessor
import shaileedemo.project.companyinfo.CompanyNewsProcessor
import shaileedemo.project.companyinfo.CompanyNewsViewModel
import shaileedemo.project.companyinfo.archmodel.NewsAction
import shaileedemo.project.companyinfo.archmodel.NewsResult


val viewModelModules: Module = module {
    viewModel{
        CompanyNewsViewModel(get())
    }
}

val mviProcessorModules : Module = module {
    factory<MviProcessor<NewsAction, NewsResult>> { CompanyNewsProcessor(get()) }
}