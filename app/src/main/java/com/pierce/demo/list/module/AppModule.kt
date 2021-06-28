package com.pierce.demo.list.module

import com.pierce.demo.list.App
import com.pierce.demo.list.data.PassRepository
import com.pierce.demo.list.data.URLRepository2
import com.pierce.demo.list.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        val application = App.getInstance()
        val repository  = PassRepository.getInstance(application)
        val urlRepo =  URLRepository2()

        MainViewModel(repository, urlRepo)
    }
}