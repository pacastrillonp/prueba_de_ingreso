package co.pacastrillonp.pruebadeingreso.ui.users

import androidx.lifecycle.ViewModel
import co.pacastrillonp.pruebadeingreso.model.User
import co.pacastrillonp.pruebadeingreso.model.UserPublicationsPresentable

class UserPublicationsViewModel : ViewModel() {
    val fetchingData = false

    val user = User(
        1,
        "Pablo Castrillón",
        "3017209503",
        "pactres@gmail.com",
    )

    val publications = listOf<UserPublicationsPresentable>(
        UserPublicationsPresentable(
            "Al seleccionar el botón “Ver publicaciones” se debe mostrar el nombre, email, teléfono y publicaciones"
        ),
        UserPublicationsPresentable(
            "Al seleccionar el botón “Ver publicaciones” se debe mostrar el nombre, email, teléfono y publicaciones"
        ),

        UserPublicationsPresentable(
            "Al seleccionar el botón “Ver publicaciones” se debe mostrar el nombre, email, teléfono y publicaciones"
        ),
        UserPublicationsPresentable(
            "Al seleccionar el botón “Ver publicaciones” se debe mostrar el nombre, email, teléfono y publicaciones"
        )
    )
}