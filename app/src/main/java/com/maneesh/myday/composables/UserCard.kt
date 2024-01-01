package com.maneesh.myday.composables

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maneesh.myday.supabase.SupaBaseClient
import com.maneesh.myday.supabase.UserActivity
import com.maneesh.myday.utils.Util
import com.maneesh.myday.viewmodels.MainViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(mainViewModel: MainViewModel, datePickerState: DatePickerState) {


    val checkBoxState = remember {
        mutableStateMapOf<String, Boolean>("Kitchen" to false, "Dining" to false)
    }

    var user by remember {
        mutableStateOf<UserActivity?>(null)
    }


    Card(elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
        Text(
            text = user?.name ?: "No data", fontWeight = FontWeight.Bold, modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        checkBoxState.forEach { me ->
            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(
                    checked = user?.tasksFinished ?: false,
                    onCheckedChange = { checkedStatus -> checkBoxState[me.key] = checkedStatus })
                Text(text = me.key)
            }
        }
    }
//    Text(text = user?.get(0)?.name ?: "Hello")
    LaunchedEffect(key1 = datePickerState.selectedDateMillis?:System.currentTimeMillis()) {
        val selectedDate = Util.getFormattedDateFromEpoch(
            datePickerState.selectedDateMillis ?: Instant.now().toEpochMilli()
        )
        Log.i("tasks",datePickerState.selectedDateMillis.toString())
        Log.i("tasks",selectedDate)
        user = SupaBaseClient.getUserActivity(selectedDate)

    }
}

@Preview
@Composable
fun UserCardPreview() {
}