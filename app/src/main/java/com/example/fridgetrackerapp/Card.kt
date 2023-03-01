package com.example.fridgetrackerapp

import android.os.Build
import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fridgetrackerapp.ui.theme.FridgeTrackerAppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

// model
data class ItemCardData(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val quantity: Int,
    val expiration: LocalDate,
    val modifier: Modifier = Modifier
)

// data
@RequiresApi(Build.VERSION_CODES.O)
fun loadItemCards(): List<ItemCardData> {
    return listOf<ItemCardData>(
        ItemCardData(
            stringResourceId = R.string.banana,
            imageResourceId = R.drawable.banana,
            quantity = 2,
            expiration = LocalDate.now()
        ),
        ItemCardData(
            stringResourceId = R.string.apple,
            imageResourceId = R.drawable.apple,
            quantity = 4,
            expiration = LocalDate.now()
        ),
        ItemCardData(
            stringResourceId = R.string.orange,
            imageResourceId = R.drawable.orange,
            quantity = 1,
            expiration = LocalDate.now()
        ),
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemCard(
    card: ItemCardData
) {
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    Card(
        modifier = card.modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painterResource(id = card.imageResourceId),
                contentDescription = stringResource(id = card.stringResourceId),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        stringResource(id = card.stringResourceId),
                        style = TextStyle(color = Color.White, fontSize = 20.sp),
                        textAlign = TextAlign.Start
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            (card.expiration.format(dateFormatter)).toString(),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                textAlign = TextAlign.End
                            )
                        )
                        Text(
                            "Qty: " + card.quantity.toString(),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                textAlign = TextAlign.End
                            )
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun ItemCardList(cardList: List<ItemCardData>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        // on below line we are adding padding
        // from all sides to our grid view.
        modifier = Modifier.padding(10.dp)
    ) {
        items(cardList) { card ->
            ItemCard(card)
        }
    }
}
