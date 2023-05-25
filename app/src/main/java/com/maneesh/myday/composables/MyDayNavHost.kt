package com.maneesh.myday.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maneesh.myday.HomeScreen
import com.maneesh.myday.WelcomeScreen

@Composable
fun MyDayNavHost(navController:NavHostController){
    fun NavHostController.navigateSingleTopTo(route:String){
        navController.navigate(route){
            launchSingleTop = true
            popUpTo(
                this@navigateSingleTopTo.graph.findStartDestination().id
            ) {
                saveState = true
            }
            restoreState=true
        }
    }
    NavHost(navController=navController, startDestination = WelcomeScreen.route){
        composable(route = WelcomeScreen.route){
            WelcomeScreen(gotoHome = {navController.navigateSingleTopTo(HomeScreen.route)})
        }
        composable(route=HomeScreen.route){
            HomeScreen()
        }
    }
}

