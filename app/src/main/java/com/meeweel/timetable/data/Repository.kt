package com.meeweel.timetable.data

import com.meeweel.timetable.domain.Class
import com.meeweel.timetable.domain.HomeWork
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getClasses(): Single<List<Class>>
    fun getHomeWorks(): Single<List<HomeWork>>
}