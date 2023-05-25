package com.maneesh.myday

interface MyDayDestinations{
    val route:String
}

object WelcomeScreen:MyDayDestinations{
    override val route="welcome_screen"
}

object HomeScreen:MyDayDestinations{
    override val route = "home_screen"
}
