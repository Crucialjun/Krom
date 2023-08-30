package ng.documenti.krom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ng.documenti.krom.ui.theme.KromTheme
import ng.documenti.krom.utils.BottomNavScreens

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

                            NavigationBar(
                                containerColor = Color.White,
                            ){
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                bottomNavItems.forEach{screen ->
                                    BottomNavigationItem(
                                        selected = currentDestination?.hierarchy?.any {
                                            it.route == screen.route
                                        } == true,
                                        label = { Text(text = screen.title) },
                                        onClick = { /*TODO*/ },
                                        icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = null, modifier = Modifier.size(24.dp)) },)
                                }
                            }

                        }
                    ) {
                        Greeting("Android",Modifier.padding(it))
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