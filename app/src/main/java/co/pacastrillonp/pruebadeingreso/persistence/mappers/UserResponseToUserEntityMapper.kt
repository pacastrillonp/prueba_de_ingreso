package co.pacastrillonp.pruebadeingreso.persistence.mappers

import co.pacastrillonp.pruebadeingreso.model.network.UserResponse
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity

fun UserResponse.userResponseToUserEntityMapper(): UserEntity =
    UserEntity(id, name, phone, email)