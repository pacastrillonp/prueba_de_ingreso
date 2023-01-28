package co.pacastrillonp.pruebadeingreso

import androidx.lifecycle.ViewModel
import co.pacastrillonp.pruebadeingreso.model.User

class MainActivityViewModel: ViewModel() {

    val fetchingData =  false

    val users = listOf<User>(
        User(1, "Pablo Castrillón", "3017209503", "pactres@gmail.com"),
        User(2, "Luisa Montoya", "3017209503", "lufermon@outlook.com"),
        User(3, "Pablo Castrillón", "3017209503", "pactres@gmail.com"),
        User(4, "Luisa Montoya", "3017209503", "lufermon@outlook.com"),
        User(1, "Pablo Castrillón", "3017209503", "pactres@gmail.com"),
        User(2, "Luisa Montoya", "3017209503", "lufermon@outlook.com"),
        User(3, "Pablo Castrillón", "3017209503", "pactres@gmail.com"),
        User(4, "Luisa Montoya", "3017209503", "lufermon@outlook.com"),
        User(1, "Pablo Castrillón", "3017209503", "pactres@gmail.com"),
        User(2, "Luisa Montoya", "3017209503", "lufermon@outlook.com"),
        User(3, "Pablo Castrillón", "3017209503", "pactres@gmail.com"),
        User(4, "Luisa Montoya", "3017209503", "lufermon@outlook.com")
    )

//    val users = emptyList<User>()
}