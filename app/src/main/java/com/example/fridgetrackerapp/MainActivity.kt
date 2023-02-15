package com.example.fridgetrackerapp

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fridgetrackerapp.ui.theme.FridgeTrackerAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

enum class MultiFloatingState {
    Expanded,
    Collapsed
}

class MinFabItem(
    val icon: ImageVector,
    val label: String,
    val identifier: String
)


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FridgeTrackerApp() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext

    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }

    val items = listOf(
        MinFabItem(
            icon = ImageVector.vectorResource(id = R.drawable.ic_camera),
            label = "Camera",
            identifier = "CameraFAB"
        ),
        MinFabItem(
            icon = ImageVector.vectorResource(id = R.drawable.ic_pencil),
            label = "Pen",
            identifier = "PenFAB"
        ),
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar {
                // open the drawer
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }

                // show a toast message when the drawer is opened (when user is toggling it)
                Toast.makeText(contextForToast, "Drawer opened", Toast.LENGTH_SHORT).show() 
            }
        },
        bottomBar = {
            BottomBar()
        },
        floatingActionButton = {
            FAB(
                multiFloatingState = multiFloatingState,
                onMultiFabStateChange = {
                    multiFloatingState = it
                },
                items = items
            )
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
fun FAB(
    multiFloatingState: MultiFloatingState,
    onMultiFabStateChange: (MultiFloatingState) -> Unit,
    items: List<MinFabItem>
) {
    val transition = updateTransition(targetState = multiFloatingState, label = "transition")

    val rotate by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 315f else 0f
    }

    val fabScale by transition.animateFloat(label = "FabScale") {
        if(it == MultiFloatingState.Expanded) 36f else 0f
    } 
    
    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = { tween(durationMillis = 500) }
    ) {
        if(it == MultiFloatingState.Expanded) 1f else 0f
    }

    val textShadow by transition.animateDp(
        label = "textShadow",
        transitionSpec = { tween(durationMillis = 100) }
    ) {
        if(it == MultiFloatingState.Expanded) 2.dp else 0.dp
    }

    Column(
        horizontalAlignment = Alignment.End,
    ) {
        if (transition.currentState == MultiFloatingState.Expanded) {
            items.forEach {
                MinFAB(
                    item = it,
                    onMinFabItemClick = {

                    },
                    fabScale = 48f,
                    alpha = alpha
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        FloatingActionButton(
            onClick = {
                onMultiFabStateChange(
                    if (transition.currentState == MultiFloatingState.Expanded) {
                        MultiFloatingState.Collapsed
                    } else {
                        MultiFloatingState.Expanded
                    }
                )
            },
            modifier = Modifier.rotate(rotate)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier.rotate(rotate)
            )
        }
    }
}

@Composable
fun MinFAB(
    item: MinFabItem,
    alpha: Float,
    //textShadow: Dp,
    fabScale: Float,
    showLabel: Boolean = true,
    onMinFabItemClick: (MinFabItem) -> Unit
) {
    val buttonColor = MaterialTheme.colors.secondary
    val shadow = Color.Black.copy(.5f)
    val painter = rememberVectorPainter(image = item.icon)
    Canvas(
        modifier = Modifier
            .size(32.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                onClick = {
                    onMinFabItemClick.invoke(item)
                },
                indication = rememberRipple(
                    bounded = false,
                    radius = 20.dp,
                    color = MaterialTheme.colors.onSurface
                )
            ),
    ) {
        drawCircle(
            color = shadow,
            radius = fabScale,
            center = Offset(
                center.x + 2f,
                center.y + 2f,
            )
        )

        drawCircle(
            color = buttonColor,
            radius = fabScale
        )

//        scale(
//            Offset(
//                center.x - (item.icon.width / 2),
//                center.y - (item.icon.width / 2),
//            )
//        ) {
//            with(painter) {
//                draw(painter.intrinsicSize)
//            }
//
//        }




//        drawImage(
//            image = item.icon,
//            topLeft = Offset(
//                center.x - (item.icon.width / 2),
//                center.y - (item.icon.width / 2),
//            ),
//            alpha = alpha
//        )
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