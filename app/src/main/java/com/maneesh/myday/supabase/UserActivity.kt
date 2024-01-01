package com.maneesh.myday.supabase

import com.fasterxml.jackson.annotation.JsonProperty


data class UserActivity(
    val id: Int?,
    @JsonProperty("date_created") val dateCreated: String,
    @JsonProperty("tasks_finished") val tasksFinished: Boolean,
    val name:String?
)

data class UA(
              val date_created: String,
              val tasks_finished: Boolean,
              val name:String?)