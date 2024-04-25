package com.loc.newsapp.presentation.navgraph

//; [{ } *" " ' ' : || \?
sealed class Route(
    val route: String
) {
    object OnBoardingScreen :Route("onBoardingScreen")
    object HomeScreen :Route("homeScreen")
    object DetailsScreen :Route("detailScreen")
    object BookmarkScreen :Route("bookMarkScreen")
    object SearchScreen :Route("searchScreen")
    object AppStartNavigation:Route("appStartNavigationScreen")
    object NewsNavigation :Route("newsApplicationScreen")
    object NewsNavigatorScreen :Route("newsNavigator")
}