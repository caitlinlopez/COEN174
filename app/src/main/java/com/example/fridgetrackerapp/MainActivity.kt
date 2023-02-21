package com.example.fridgetrackerapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.fridgetrackerapp.ui.theme.FridgeTrackerAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

val showStorageTest: MutableState<Boolean> = mutableStateOf(false)

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

sealed class MultiFabState {
    object Collapsed: MultiFabState()
    object Expand: MultiFabState()

    fun isExpanded() = this == Expand

    fun toggleValue() = if (isExpanded()) {
        Collapsed
    } else {
        Expand
    }
}

@Composable
fun rememberMultiFabState() = remember { mutableStateOf<MultiFabState>(MultiFabState.Collapsed) }

data class MultiFabItem(
    val id: Int,
    @DrawableRes val iconRes: Int,
    val label: String = "",
)

@Immutable
interface FabOption {
    @Stable val iconTint: Color
    @Stable val backgroundTint: Color
    @Stable val showLabel: Boolean
}

private class FabOptionImpl(
    override val iconTint: Color,
    override val backgroundTint: Color,
    override val showLabel: Boolean
) : FabOption

@SuppressLint("ComposableNaming")
@Composable
fun FabOption(
    backgroundTint: Color = MaterialTheme.colors.primary,
    iconTint: Color = contentColorFor(backgroundColor = backgroundTint),
    showLabel: Boolean = false
): FabOption = FabOptionImpl(iconTint, backgroundTint, showLabel)

@Immutable
interface FabIcon {
    @Stable
    val iconRes: Int
    @Stable val iconRotate: Float?
}

private class FabIconImpl(
    override val iconRes: Int,
    override val iconRotate: Float?
) : FabIcon

fun FabIcon(@DrawableRes iconRes: Int, iconRotate: Float? = null): FabIcon =
    FabIconImpl(iconRes, iconRotate)



@OptIn(ExperimentalAnimationApi::class)
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
            MultiFloatingActionButton(
                items = listOf(
                    MultiFabItem(
                        id = 1,
                        iconRes = R.drawable.ic_pencil,
                        label = "Enter Manually",
                    ),
                    MultiFabItem(
                        id = 2,
                        iconRes = R.drawable.ic_camera,
                        label = "Scan Barcode",
                    ),
                ),
                fabIcon = FabIcon(iconRes = R.drawable.ic_baseline_add_24, iconRotate = 45f),
                onFabItemClicked = {
                    Toast.makeText(contextForToast, it.label, Toast.LENGTH_LONG).show()
                    if (it.label == "Enter Manually") {
                        Log.d("TAG", "label correct")
                        showStorageTest.value = true
                    } else {
                        showStorageTest.value = false
                    }
                },
                fabOption = FabOption(
                    iconTint = Color.White,
                    showLabel = true
                )
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
    if (showStorageTest.value) {
        saveDataToExternalStorage(context = LocalContext.current, msg = "test")
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

@ExperimentalAnimationApi
@Composable
fun MultiFloatingActionButton(
    modifier: Modifier = Modifier,
    items: List<MultiFabItem>,
    fabState: MutableState<MultiFabState> = rememberMultiFabState(),
    fabIcon: FabIcon,
    fabOption: FabOption = FabOption(),
    onFabItemClicked: (fabItem: MultiFabItem) -> Unit,
    stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
    val rotation by animateFloatAsState(
        if (fabState.value == MultiFabState.Expand) {
            fabIcon.iconRotate ?: 0f
        } else {
            0f
        }
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = fabState.value.isExpanded(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(items.size) { index ->
                    MiniFabItem(
                        item = items[index],
                        fabOption = fabOption,
                        onFabItemClicked = onFabItemClicked
                    )
                }

                item {}
            }
        }

        FloatingActionButton(
            onClick = {
                fabState.value = fabState.value.toggleValue()
                stateChanged(fabState.value)
            },
            backgroundColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint
        ) {
            Icon(
                painter = painterResource(id = fabIcon.iconRes),
                contentDescription = "FAB",
                modifier = Modifier.rotate(rotation),
                tint = fabOption.iconTint
            )
        }
    }
}

@Composable
fun MiniFabItem(
    item: MultiFabItem,
    fabOption: FabOption,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (fabOption.showLabel) {
            Text(
                text = item.label,
                style = MaterialTheme.typography.h6,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            )
        }

        FloatingActionButton(
            onClick = {
                onFabItemClicked(item)
            },
            modifier = Modifier.size(40.dp),
            backgroundColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint
        ) {
            Icon(
                painter = painterResource(id = item.iconRes),
                contentDescription = item.label,
                tint = fabOption.iconTint
            )
        }
    }
}

@Composable
fun BottomBar() {
    val bottomMenuItemsList = prepareBottomMenu()

    var selectedItem by remember {
        mutableStateOf("Home")
    }

    val context = LocalContext.current

    BottomNavigation() {
        bottomMenuItemsList.forEach() { menuItem ->
            BottomNavigationItem(
                selected = (selectedItem == menuItem.label),
                onClick = {
                    selectedItem = menuItem.label
                    //Toast.makeText(context, menuItem.label, Toast.LENGTH_LONG).show()
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

//storage

@Composable
fun saveDataToExternalStorage(context: Context, msg: String) {
    // on below line creating a variable for message.
    val message = remember {
        mutableStateOf("")
    }
    val txtMsg = remember {
        mutableStateOf("")
    }
    val activity = context as Activity

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        // on below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = "Internal Storage in Android",
            // on below line we are specifying text color,
            // font size and font weight
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(10.dp))
        // on below line we are creating a text field
        // for our message number.
        TextField(
            // on below line we are specifying value
            // for our message text field.
            value = message.value,
            // on below line we are adding on value
            // change for text field.
            onValueChange = { message.value = it },
            // on below line we are adding place holder as
            // text as "Enter your email"
            placeholder = { Text(text = "Enter your message") },
            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth().testTag("wordField"),
            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            // on below line we ar adding single line to it.
            singleLine = true,
        )

        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(10.dp))


        // on below line adding a button.
        Button(
            onClick = {
                // on below line we are saving data to internal storage
                try {
                    val fos: FileOutputStream =
                        context.openFileOutput("demoFile.txt", Context.MODE_PRIVATE)
                    fos.write(message.value.toByteArray())
                    fos.flush()
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                message.value = ""
                Toast.makeText(context, "Data saved successfully..", Toast.LENGTH_SHORT).show()

            },
            // on below line adding a modifier for our button.
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            // on below line adding a text for our button.
            Text(text = "Write Data to Internal Storage", textAlign = TextAlign.Center)
        }
        // on below line adding a spacer
        Spacer(modifier = Modifier.width(10.dp))

        // on below line adding a text view to display our data.
        Text(
            // on below line we are specifying text
            text = "Data will appear below : \n" + txtMsg.value,
            // on below line we are specifying text color,
            // font size and font weight
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        // adding spacer on below line.
        Spacer(modifier = Modifier.height(20.dp))
        // on below line creating a button
        Button(
            onClick = {
                // on below line we are reading the
                // data which we stored in our file.
                try {
                    val fin: FileInputStream = context.openFileInput("demoFile.txt")
                    var a: Int
                    val temp = StringBuilder()
                    while (fin.read().also { a = it } != -1) {
                        temp.append(a.toChar())
                    }

                    txtMsg.value = temp.toString()
                    fin.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            },
            // on below line adding a
            // modifier for our button.
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            // on below line adding a text for our button.
            Text(text = "Read Data from Internal Storage", textAlign = TextAlign.Center)
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