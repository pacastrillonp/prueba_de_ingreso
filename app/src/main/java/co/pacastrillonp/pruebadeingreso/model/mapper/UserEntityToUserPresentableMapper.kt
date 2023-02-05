package co.pacastrillonp.pruebadeingreso.model.mapper

import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity

fun UserEntity.userEntityToUserPresentable(): UserPresentable = UserPresentable(
    id = id,
    name = name ?: "",
    telephoneNumber = telephoneNumber ?: "",
    emailAddress = emailAddress ?: ""
)