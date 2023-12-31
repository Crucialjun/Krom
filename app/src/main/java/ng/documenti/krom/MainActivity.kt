package ng.documenti.krom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ng.documenti.krom.features.animelist.presentation.ui.AnimeListScreen
import ng.documenti.krom.features.profile.presentation.ui.ProfileScreen
import ng.documenti.krom.features.uploads.presentation.ui.UploadsScreen
import ng.documenti.krom.ui.theme.KromTheme
import ng.documenti.krom.utils.BottomNavScreens

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomNavItems = listOf(
            BottomNavScreens.Home,
            BottomNavScreens.Uploads,
            BottomNavScreens.Profile
        )
        setContent {
            KromTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,


                    ) {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = {

                            BottomNavigation(
                                backgroundColor = Color.White
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                bottomNavItems.forEach { screen ->
                                    BottomNavigationItem(
                                        selected = currentDestination?.hierarchy?.any {
                                            it.route == screen.route
                                        } == true,
                                        label = { Text(text = screen.title) },
                                        onClick = { navController.navigate(screen.route){
                                            popUpTo(navController.graph.findStartDestination().id){
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        } },
                                        icon = {
                                            Icon(
                                                painter = painterResource(id = screen.icon),
                                                contentDescription = null,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        },
                                    )
                                }
                            }

                        }
                    ) {
                        NavHost(navController = navController, startDestination = BottomNavScreens.Home.route, modifier = Modifier.padding(it)){
                            composable(BottomNavScreens.Home.route) {
                                AnimeListScreen()
                            }
                            composable(BottomNavScreens.Uploads.route) {
                               UploadsScreen()
                            }
                            composable(BottomNavScreens.Profile.route) {
                                ProfileScreen()
                            }

                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KromTheme {
        Greeting("Android")
    }
}