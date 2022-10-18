package com.example.artspace

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtWorkWall(image: Painter, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,

        ) {
        Box(
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.Black))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .padding(40.dp)
            )
        }
    }
}

@Composable
fun ArtWorkDescription(title: String, author: String, year: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .shadow(elevation = 2.dp)
            .padding(24.dp)
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Justify,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(text = author, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "($year)")
        }

    }
}

@Composable
fun ArtWorkButtons(
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val windowSize = Resources.getSystem().displayMetrics.widthPixels

    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Bottom

    ) {
        Button(
            modifier = Modifier
                .weight(1f),
            onClick = onPrevClick
        ) {
            Text(text = "Previous")
        }
        if (windowSize > 800) {
            Spacer(modifier = Modifier.width(800.dp))
        } else {
            Spacer(modifier = Modifier.width(32.dp))
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun ArtWork(
    title: String,
    author: String,
    year: Int,
    image: Painter,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtWorkWall(image = image, modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(32.dp))

        ArtWorkDescription(title = title, author = author, year = year)

        Spacer(modifier = Modifier.height(32.dp))

        ArtWorkButtons(onNextClick = onNextClick, onPrevClick = onPrevClick)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpaceApp() {
    var index by remember { mutableStateOf(0) }

    val artWorks = arrayOf(
        arrayOf(
            "Great Sand Dunes National Park, Colorado",
            "Alex Person",
            2022,
            R.drawable.alex_person_dune
        ),
        arrayOf(
            "Window with blue and white curtain in Venice, Italy.",
            "Taylor Simpson",
            2022,
            R.drawable.taylor_simpson_window
        ),
        arrayOf(
            "Autumn images of the beautiful Cheia road in Romania.",
            "Adrian Dascal",
            2022,
            R.drawable.adrian_dascal_road
        ),
        arrayOf(
            "Beach resort in Heraklion, Crete island, Greece.",
            "Geio Tischler",
            2022,
            R.drawable.geio_tischler_beach
        ),
        arrayOf(
            "Dark forest fern",
            "Andy Feliciotti",
            2022,
            R.drawable.andy_feliciotti_forest
        ),
    )
    val (title, author, year, image) = artWorks[index]


    ArtWork(
        title = title.toString(),
        author = author.toString(),
        year = year.hashCode(),
        image = painterResource(image.hashCode()),
        onPrevClick = {
            if (index <= 0) index = artWorks.size - 1 else index--
        },
        onNextClick = {
            if (index > artWorks.size - 2) index = 0 else index++
        }
    )

}

