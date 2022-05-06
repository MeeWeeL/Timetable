package com.meeweel.timetable.ui.fragmenthome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.timetable.data.Repository
import com.meeweel.timetable.data.RepositoryImplFake
import com.meeweel.timetable.domain.ClassesState
import com.meeweel.timetable.domain.HomeworkState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val repository: Repository = RepositoryImplFake()) : ViewModel() {

    private val liveDataClasses: MutableLiveData<ClassesState> = MutableLiveData()
    private val liveDataHomework: MutableLiveData<HomeworkState> = MutableLiveData()

    fun getClassesData(): LiveData<ClassesState> {
        return liveDataClasses
    }
    fun getHomeworkData(): LiveData<HomeworkState> {
        return liveDataHomework
    }

    fun getClasses() = getClassesFromRepo()
    fun getHomework() = getHomeworkFromRepo()

    private fun getClassesFromRepo() {
        repository.getClasses()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataClasses.postValue(ClassesState.Loading)
            }
            .subscribe({
                liveDataClasses.postValue(ClassesState.Success(it))
            }, {
                liveDataClasses.postValue(ClassesState.Error(Throwable("Connection error")))
            })
    }

    private fun getHomeworkFromRepo() {
        repository.getHomeWorks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataHomework.postValue(HomeworkState.Loading)
            }
            .subscribe({
                liveDataHomework.postValue(HomeworkState.Success(it))
            }, {
                liveDataHomework.postValue(HomeworkState.Error(Throwable("Connection error")))
            })
    }
}