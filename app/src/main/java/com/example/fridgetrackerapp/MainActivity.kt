package com.example.fridgetrackerapp

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
<<<<<<< Updated upstream
=======
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.fillMaxSize
>>>>>>> Stashed changes
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fridgetrackerapp.ui.theme.FridgeTrackerAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
<<<<<<< Updated upstream
=======
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import com.example.fridgetrackerapp.presentation.MainScreen
import dagger.hilt.android.AndroidEntryPoint

val showStorageTest: MutableState<Boolean> = mutableStateOf(false)
val barcodeScreen: MutableState<Boolean> = mutableStateOf(false)
>>>>>>> Stashed changes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    FridgeTrackerAppTheme {
        FridgeTrackerApp()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FridgeTrackerApp() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar {
                // open the drawer
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        bottomBar = {
            BottomBar()
        },
        floatingActionButton = {
<<<<<<< Updated upstream
            FAB()
=======
            MultiFloatingActionButton(
                items = listOf(
                    MultiFabItem(
                        id = 1,
                        iconRes = R.drawable.ic_pencil,
                        label = "Enter Manually"
                    ),
                    MultiFabItem(
                        id = 2,
                        iconRes = R.drawable.ic_camera,
                        label = "Scan Barcode"
                    ),
                ),
                fabIcon = FabIcon(iconRes = R.drawable.ic_baseline_add_24, iconRotate = 45f),
                onFabItemClicked = {
                    Toast.makeText(contextForToast, it.label, Toast.LENGTH_LONG).show()
                    if (it.label == "Enter Manually") {
                        Log.d("TAG", "label correct")
                        showStorageTest.value = true
                    } else {
                        barcodeScreen.value = true
                    }
                },
                fabOption = FabOption(
                    iconTint = Color.White,
                    showLabel = true
                )
            )
>>>>>>> Stashed changes
        },
        drawerContent = {
            NavDrawer(
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
        },
        drawerGesturesEnabled = true,
    ) {

    }
    if (barcodeScreen.value) {
        MainScreen()
    }
}

@Composable
fun TopBar(onNavigationIconClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Fridge Tracker",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigationIconClick()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Menu, contentDescription = "navigation"
                )
            }
        },
    )
}

@Composable
fun NavDrawer(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // nav drawer text
        Text(text = "nav")

        // gap between text and button
        Spacer(modifier = Modifier.height(height = 32.dp))

        // button to close
        Button(onClick = {
            coroutineScope.launch {
                scaffoldState.drawerState.close()
            }
        }) {
            Text(text = "Close")
        }

    }
}

@Composable
fun FAB() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun BottomBar() {
    val bottomMenuItemsList = prepareBottomMenu()

    var selectedItem by remember {
        mutableStateOf("Home")
    }

    BottomNavigation() {
        bottomMenuItemsList.forEach() { menuItem ->
            BottomNavigationItem(
                selected = (selectedItem == menuItem.label),
                onClick = {
                    selectedItem = menuItem.label
                },
                icon = {
                    Icon(
                        imageVector = menuItem.icon,
                        contentDescription = menuItem.label,
                    )
                }
            )
        }
    }
}

private fun prepareBottomMenu(): List<BottomMenuItem> {
    val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

    // add menu items
    bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home))
    bottomMenuItemsList.add(BottomMenuItem(label = "Profile", icon = Icons.Filled.Person))
    bottomMenuItemsList.add(BottomMenuItem(label = "Cart", icon = Icons.Filled.ShoppingCart))
    bottomMenuItemsList.add(BottomMenuItem(label = "Settings", icon = Icons.Filled.Settings))

    return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: ImageVector)