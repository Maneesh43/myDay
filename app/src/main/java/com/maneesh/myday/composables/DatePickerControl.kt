package com.maneesh.myday.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerControl(datePickerState:DatePickerState,isShowDatePicker:MutableState<Boolean>){
    AnimatedVisibility(visible = isShowDatePicker.value) {
        DatePickerDialog(onDismissRequest = { isShowDatePicker.value=false }, confirmButton = {
            Button(onClick = {
                isShowDatePicker.value = false

            }) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "OK")
            }
        }) {

            DatePicker(state = datePickerState)
        }
    }
}