package co.pacastrillonp.pruebadeingreso.network

@Suppress("UNINITIALIZED_ENUM_COMPANION_WARNING")
enum class Endpoint(
    val path: String
) {

    Users(Endpoint.USERS),
    Posts(Endpoint.POTS);

    companion object {
        const val USERS = "users"
        const val POTS = "posts"

        fun get(encodedPath: String) =
            enumValues<Endpoint>().firstOrNull { encodedPath.endsWith(it.path) }
    }
}
