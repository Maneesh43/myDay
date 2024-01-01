package com.maneesh.myday.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.serializer.JacksonSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate


object SupaBaseClient {
    private val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://ucdobibjwsiscnkiqoln.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVjZG9iaWJqd3Npc2Nua2lxb2xuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDM4MjAwNjEsImV4cCI6MjAxOTM5NjA2MX0.DJKgJN04glApgtdkEg0g_aHrCzkGiSbeytLcHDDphME"
    ) {
        install(Postgrest)
        defaultSerializer = JacksonSerializer()
    }

    suspend fun getUserActivity(selectedDate: String): UserActivity? {
        return withContext(Dispatchers.IO) {
            try {
                val userActivity =
                    SupaBaseClient.supabaseClient.from("userActivity").select() {
                        filter {
                            eq("date_created", selectedDate)
                        }
                    }.decodeSingleOrNull<UserActivity>()
                userActivity
            } catch (e: io.github.jan.supabase.exceptions.HttpRequestException) {
                null
            }
        }
    }

    suspend fun setUserActivity(person: String): UserActivity {
        val userActivity =
            UA(date_created = LocalDate.now().toString(), name = person, tasks_finished = true)
        val result = supabaseClient.from("userActivity").insert(userActivity) {
            select()
        }.decodeSingle<UserActivity>()
        return result
    }
}