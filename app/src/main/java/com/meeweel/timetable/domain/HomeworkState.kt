package com.meeweel.timetable.domain

sealed class HomeworkState {
    data class Success(val data: List<HomeWork>) : HomeworkState()
    class Error(val error: Throwable) : HomeworkState()
    object Loading : HomeworkState()
}