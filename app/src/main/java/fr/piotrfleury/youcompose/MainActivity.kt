package fr.piotrfleury.youcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.piotrfleury.youcompose.presentation.VideoScreen
import fr.piotrfleury.youcompose.presentation.VideosScreen
import fr.piotrfleury.youcompose.ui.theme.YouComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YouComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "videos") {
                        composable("videos") { VideosScreen( navController ) }
                        composable("videos/{videoId}") { backStackEntry ->  VideoScreen(backStackEntry.arguments?.getString("videoId")!!)  }
                    }
                }
            }
        }
    }
}