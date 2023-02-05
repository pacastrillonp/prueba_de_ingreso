package co.pacastrillonp.pruebadeingreso.di

import co.pacastrillonp.pruebadeingreso.ui.main.mainModule
import co.pacastrillonp.pruebadeingreso.network.networkModule
import co.pacastrillonp.pruebadeingreso.persistence.persistenceModule
import co.pacastrillonp.pruebadeingreso.repository.repositoryModule
import co.pacastrillonp.pruebadeingreso.ui.users.userPublicationsModule

val applicationModule =
    mainModule + networkModule + repositoryModule + persistenceModule + userPublicationsModule