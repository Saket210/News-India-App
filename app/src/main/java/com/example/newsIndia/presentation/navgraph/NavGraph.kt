package com.example.newsIndia.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsIndia.presentation.news_navigator.NewsNavigator
@Composable
fun NavGraph(
    startDestination: String
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        navigation(route = Route.NewsNavigation.route, startDestination = Route.NewsNavigationScreen.route){
            composable(route = Route.NewsNavigationScreen.route){
                NewsNavigator()
            }
        }
    }
}