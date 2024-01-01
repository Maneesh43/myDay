package com.maneesh.myday.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maneesh.myday.viewmodels.MainViewModel
import java.time.LocalDate
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(datePickerState: DatePickerState,mainViewModel: MainViewModel) {
    val isShowDatePicker = remember {
        mutableStateOf(false)
    }




    Column(modifier = Modifier.padding(10.dp)) {
        DatePickerUI(datePickerState, isShowDatePicker)
        UserCard(mainViewModel,datePickerState)
    }

}

@Preview
@Composable
fun HomeScreenContentPreview() {
    ElevatedCard {
        Text(text = "Hello")
    }
}