package shaileedemo.project.companyinfo.archmodel

import shaileedemo.project.common.mvibase.MviIntent

sealed class NewsIntent : MviIntent{
    data object InitialIntent : NewsIntent()
    data object GetNextCompanyNews : NewsIntent()
}