package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

//; [{ } *" " ' ' : || \?
data class Page(
    val title : String,
    val description :String,
    @DrawableRes val image : Int
)

val pages = listOf(
    Page(
        title = "OnBoarding Screen 1",
        description = " Description Screen 1 and a lot of members in here blah blah try it out",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "OnBoarding Screen 2",
        description = " Description Screen 2 and alot of members in here blah blah try it out",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "OnBoarding Screen 3",
        description = " Description Screen 3 and alot of members in here blah blah try it out",
        image = R.drawable.onboarding3
    )
)