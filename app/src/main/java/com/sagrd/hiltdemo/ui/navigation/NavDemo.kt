package com.sagrd.hiltdemo.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavDemo() {
  /*  val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "ScreenOne") {
        composable("ScreenOne") {
            ScreenOne(
                onNavigate = {
                    navController.navigate("ScreenTwo")
                }
            )
        }

    }*/
}

val NavHostController.canNavigateBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED