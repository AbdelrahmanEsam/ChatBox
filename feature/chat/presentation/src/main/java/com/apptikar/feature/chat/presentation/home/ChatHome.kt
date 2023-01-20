package com.apptikar.feature.chat.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apptikar.designsystem.theme.GrayOr
import com.apptikar.designsystem.theme.TextGreen
import com.apptikar.designsystem.theme.White
import com.apptikar.feature.chat.presentation.state.BottomScreen

//@Composable
//fun ChatHome(navController:NavHostController = rememberNavController()) {
//    Scaffold(
//        bottomBar = { BottomNavBar(navController = navController) }
//    ) {
//            paddingValues ->
//
//
//          HomeNavHost(modifier = Modifier.padding(paddingValues), navController = navController,)
//    }
//}


@Composable
fun BottomNavBar(navController: NavHostController) {

    val screens = listOf(BottomScreen.Home, BottomScreen.Calls, BottomScreen.Contacts)

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    Row(modifier = Modifier
        .wrapContentSize()
        .background(Color.White)) {
        BottomNavigation( backgroundColor = White) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }



}

@Composable
fun RowScope.AddItem(
    screen: BottomScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
)
{

    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
    it.route == screen.route
    } == true
        , icon =
        {
            Icon(painter = painterResource(id = screen.icon), contentDescription = stringResource(screen.title) )

        }
        ,onClick = {

       navController.navigate(screen.route){
           popUpTo(navController.graph.findStartDestination().id)
           launchSingleTop = true

       }

        },

        label = {

            Text(text = stringResource(id = screen.title), style = MaterialTheme.typography.h5.copy(fontSize = 12.sp))
        },
        unselectedContentColor = GrayOr,
        selectedContentColor = TextGreen,


    )


//    BottomNavigation(
//
//
//    ) {
//        Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Spacer(modifier = Modifier.size(10.dp))
//            Icon(painter = painterResource(id = screen.icon), contentDescription = stringResource(screen.title) )
//            Spacer(modifier = Modifier.size(10.dp))
//            Text(text = stringResource(id = screen.title), style = MaterialTheme.typography.h5.copy(color = GrayOr))
//            Spacer(modifier = Modifier.size(10.dp))
//        }
//    }



}