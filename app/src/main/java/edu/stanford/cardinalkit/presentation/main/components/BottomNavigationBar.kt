package edu.stanford.cardinalkit.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier:Modifier){
    BottomNavigation {
        val COLOR_NORMAL = Color.Gray
        val COLOR_SELECTED = Color(0xFF484965)
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        Row(modifier = modifier.background(Color.White)){
            TabBarItems.BarItems.forEach { navItem ->
                BottomNavigationItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },

                    icon = {
                        Icon(
                            imageVector = navItem.image,
                            contentDescription = navItem.title,
                        )
                    },
                    label = {
                        Text(text = navItem.title, color =Color.Gray)
                    },
                    selectedContentColor = COLOR_SELECTED,
                    unselectedContentColor = COLOR_NORMAL
                )
            }

        }


    }
}
