package com.meeweel.timetable.data

import com.meeweel.timetable.domain.Class
import com.meeweel.timetable.domain.FakeRepository
import com.meeweel.timetable.domain.HomeWork
import io.reactivex.rxjava3.core.Single

class RepositoryImplFake(private val repo: FakeRepository = FakeRepository()) : Repository {
    override fun getClasses(): Single<List<Class>> {
        return repo.getClasses()
    }

    override fun getHomeWorks(): Single<List<HomeWork>> {
        return repo.getHomeworks()
    }
}