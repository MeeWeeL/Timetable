package com.meeweel.timetable.domain

import io.reactivex.rxjava3.core.Single

class FakeRepository {

    private val classes = listOf(Class("Math", "08:00-08:45","Very long description of this class"),
        Class("Math", "08:00-08:45","Very long description of this class"),
        Class("Literature", "09:00-09:45","Very long description of this class"),
        Class("Sport", "10:00-10:45","Very long description of this class"),
        Class("Physics", "11:00-11:45","Very long description of this class"),
        Class("Math", "12:00-12:45","Very long description of this class"),
        Class("Literature", "13:00-13:45","Very long description of this class"),
        Class("History", "14:00-14:45","Very long description of this class"))

    private val homework = listOf(HomeWork("Physics", "1 day left","Very long description of this class"),
        HomeWork("Math", "3 day left","Very long description of this class"),
        HomeWork("Literature", "4 day left","Very long description of this class"),
        HomeWork("Sport", "5 day left","Very long description of this class"),
        HomeWork("Physics", "7 day left","Very long description of this class"),
        HomeWork("Math", "8 day left","Very long description of this class"),
        HomeWork("Literature", "9 day left","Very long description of this class"),
        HomeWork("History", "11 day left","Very long description of this class"))

    fun getClasses(): Single<List<Class>> = Single.just(classes)
    fun getHomeworks(): Single<List<HomeWork>> = Single.just(homework)
}