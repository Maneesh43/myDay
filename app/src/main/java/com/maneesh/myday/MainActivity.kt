package com.maneesh.myday

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.maneesh.myday.composables.MyDayNavHost
import com.maneesh.myday.ui.theme.MyDayTheme
import com.maneesh.myday.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // At the top level of your kotlin file:
        setContent {
            val mainViewModel by viewModels<MainViewModel>()
            MyDayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold {
                    MyDayNavHost(navController = rememberNavController(),mainViewModel)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    MyDayTheme {
        Surface(modifier = Modifier.fillMaxSize(),contentColor = MaterialTheme.colorScheme.surface) {

        }
    }
}