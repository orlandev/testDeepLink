package com.orlandev.testdeeplink

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink


@Composable
fun NavGraph(startDestination: String = "home") {
    val navController = rememberNavController()
    val uri = "https://test.com"
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = "home") {
            Home()
        }

        composable(
            route = "app_detail={detail_id}",
            arguments = listOf(navArgument("detail_id") { type = NavType.LongType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/detail_id={detail_id}" })
        ) { backStackEntry ->
            val arguments = backStackEntry.arguments
            Detail(taskId = arguments?.getLong("detail_id"))
        }

        composable(route = "about") {
            About()
        }
    }
}

@Composable
fun Home() {
    Box(modifier = Modifier) {
        Text(text = "HOME SCREEN")
    }
}

@Composable
fun About() {
    Box(modifier = Modifier) {
        Text(text = "ABOUT SCREEN")
    }
}

@Composable
fun Detail(taskId: Long?) {
    Box(modifier = Modifier) {
        Column(modifier = Modifier) {
            Text(text = "DETAIL SCREEN: ID: $taskId")
            Text(text = "Se accede usando un DeepLink")
        }
    }
}
