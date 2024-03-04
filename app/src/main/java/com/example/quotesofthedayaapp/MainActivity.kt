package com.example.quotesofthedayaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotesofthedayaapp.ui.theme.Background
import com.example.quotesofthedayaapp.ui.theme.CardColor
import com.example.quotesofthedayaapp.ui.theme.QuotesOfTheDayaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesOfTheDayaAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
@Composable
fun MainScreen(){
    val quotes = getQuotes()
    var quote by remember {
        mutableStateOf(quotes.random())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Quote of the day",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Black,
            modifier = Modifier.padding(top = 20.dp,bottom = 50.dp)
        )

        QuoteCard(quote=quote)

        IconButton(
            onClick = {
                quote = quotes.random()
            },
            modifier = Modifier
                .padding(top = 40.dp, bottom = 20.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CardColor)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "refresh",
                modifier= Modifier,
                tint = Black
            )
        }
    }
}

fun getQuotes(): List<Quote> = listOf(
    Quote(quote = "Some people see innovation as change, but we have never really seen it like that. It’s making things better", author = "Tim Cook"),
    Quote(quote = "Change will not come if we wait for some other person, or if we wait for some other time. We are the ones we've been waiting for. We are the change that we seek.", author = "Barack Obama"),
    Quote(quote = "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.", author = "Albert Einstein"),
    Quote(quote = "You have to understand, most people are not ready to be unplugged...", author = "Captain Morpheus"),
    Quote(quote = "In a world filled with hate, we must still dare to hope. In a world filled with anger, we must still dare to comfort. In a world filled with despair, we must still dare to dream. And in a world filled with distrust, we must still dare to believe.", author = "Michael Jackson"),
)

@Composable
fun QuoteCard(quote: Quote) {
    Surface(
        color = CardColor,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 30.dp)
        ) {
            Text(
                text = quote.quote,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                lineHeight = 50.sp,
                modifier = Modifier.padding(bottom = 60.dp)
            )
            Text(
                text = quote.author,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Black.copy(0.5f),
            )
        }
    }
}
