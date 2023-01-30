package co.pacastrillonp.pruebadeingreso.persistence.mappers

import co.pacastrillonp.pruebadeingreso.model.UserPublicationsPresentable
import co.pacastrillonp.pruebadeingreso.model.network.PostResponse

fun PostResponse.postResponseToUserPublicationsMapper(
    name: String,
    telephoneNumber: String,
    emailAddress: String
): UserPublicationsPresentable =
    UserPublicationsPresentable(
        name = name,
        phone = telephoneNumber,
        email = emailAddress,
        publication = body,
    )