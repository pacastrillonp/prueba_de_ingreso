package co.pacastrillonp.pruebadeingreso.model.mapper

import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity

fun UserEntity.userEntityToUserPresentableMapper(): UserPresentable = UserPresentable(
    id = id,
    name = name ?: "",
    telephoneNumber = telephoneNumber ?: "",
    emailAddress = emailAddress ?: ""
)