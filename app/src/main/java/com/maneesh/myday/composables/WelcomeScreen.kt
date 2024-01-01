package com.maneesh.myday.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maneesh.myday.ui.theme.MyDayTheme
import com.maneesh.myday.viewmodels.MainViewModel

@Composable
fun WelcomeScreen(gotoHome: () -> Unit) {
    val vm: MainViewModel = viewModel()

    val quote by vm.quote.collectAsStateWithLifecycle()

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,

            ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = quote,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(20.dp)
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                    if (quote.isNotEmpty()) {
                        IconButton(onClick = gotoHome) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Next",
                                modifier = Modifier.size(120.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }


    LaunchedEffect(key1 = Unit) {
        if(quote.isEmpty()){
            vm.getQuote()
        }
    }

}


@Preview
@Composable
fun DefaultPreview() {
    MyDayTheme {
        Surface {

        }
    }
}
