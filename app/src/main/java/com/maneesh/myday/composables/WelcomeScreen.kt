package com.maneesh.myday.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maneesh.myday.ui.theme.MyDayTheme
import com.maneesh.myday.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun WelcomeScreen(gotoHome: () -> Unit) {
    val vm: MainViewModel = viewModel()

    val quote by vm.quote.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.background(color = Color(0xFF412722))){

        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFE3D87E)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 60.sp)) {
                            withStyle(style = SpanStyle(color = Color(0xFFD81E5B))) {
                                append("H")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFFD81E5B))) {
                                append("e")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFFD81E5B))) {
                                append("y")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFFD81E5B))) {
                                append("!")
                            }
                        }
                    }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F), contentAlignment = Alignment.Center
            ) {
                Text(text = quote)
            }

            Box(
                contentAlignment = Alignment.BottomEnd, modifier = Modifier
                    .weight(1F)
                    .padding(bottom = 20.dp)
            ) {
                Button(onClick = gotoHome, modifier = Modifier) {
                    Text(text = "Next")
                }
            }
        }

    }

    LaunchedEffect(key1 = Unit){
        vm.getQuote()
    }

}


@Preview
@Composable
fun DefaultPreview() {
    MyDayTheme {
        WelcomeScreen {

        }
    }
}
