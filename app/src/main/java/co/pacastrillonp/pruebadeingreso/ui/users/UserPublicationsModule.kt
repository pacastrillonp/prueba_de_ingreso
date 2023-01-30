package co.pacastrillonp.pruebadeingreso.ui.users

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userPublicationsModule = module {
    viewModel { UserPublicationsViewModel(get()) }
}