package com.bojanvilic.film.ui

enum class TopLevelDestinations(
    val title: String,
    val route: String
) {
    All(
        title = "All",
        route = "all_route"
    ),
    General(
        title = "General",
        route = "general_route"
    ),
    Requests(
        title = "Requests",
        route = "requests_route"
    );

    companion object {
        fun fromRoute(route: String?): TopLevelDestinations? =
            when (route?.substringBefore("/")) {
                All.route -> All
                General.route -> General
                Requests.route -> Requests
                "root_graph" -> All
                null -> All
                else -> null
            }
    }
}