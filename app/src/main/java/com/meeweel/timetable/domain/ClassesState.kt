package com.meeweel.timetable.domain

sealed class ClassesState {
    data class Success(val data: List<Class>) : ClassesState()
    class Error(val error: Throwable) : ClassesState()
    object Loading : ClassesState()
}