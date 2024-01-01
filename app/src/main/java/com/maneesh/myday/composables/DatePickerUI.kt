package com.maneesh.myday.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maneesh.myday.utils.Util
import java.time.LocalDate
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerUI(datePickerState: DatePickerState, isShowDatePicker: MutableState<Boolean>) {
    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 10.dp)){

        val modifier = Modifier.size(20.dp)
        IconButton(onClick = {
            datePickerState.setSelection(Util.getPreviousDayEpoch(datePickerState.selectedDateMillis!!))
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous",
                modifier = modifier
            )
        }
        Text(text = Util.getFormattedDateFromEpoch(datePickerState.selectedDateMillis!!))
        IconButton(onClick = {
            datePickerState.setSelection(Util.getNextDayEpoch(datePickerState.selectedDateMillis!!))
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next",
                modifier=modifier
            )

        }
        IconButton(onClick = { isShowDatePicker.value = true }) {
            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar")
            DatePickerControl(
                datePickerState = datePickerState,
                isShowDatePicker = isShowDatePicker
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun datePickerUIPreview() {
    val datePickerState = rememberDatePickerState()
    val isShowDatePicker = mutableStateOf(false)
    DatePickerUI(datePickerState, isShowDatePicker)
}