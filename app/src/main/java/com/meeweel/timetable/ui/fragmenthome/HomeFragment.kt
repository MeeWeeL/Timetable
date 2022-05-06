package com.meeweel.timetable.ui.fragmenthome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meeweel.timetable.databinding.FragmentMainLayoutBinding
import com.meeweel.timetable.domain.ClassesState
import com.meeweel.timetable.domain.HomeworkState

class HomeFragment : Fragment() {

    private var _bind: FragmentMainLayoutBinding? = null
    private val bind get() = _bind!!

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val classesAdapter = HomeClassesRecyclerAdapter()
    private val homeworkAdapter = HomeHomeworkRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentMainLayoutBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.classesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bind.homeworkRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        workLivedata()
    }

    private fun workLivedata() {
        bind.classesRecycler.adapter =
            classesAdapter
        bind.homeworkRecycler.adapter =
            homeworkAdapter

        val classesObserver =
            Observer<ClassesState> { a ->
                renderClassesData(a)
            }
        val homeworkObserver =
            Observer<HomeworkState> { a ->
                renderHomeworkData(a)
            }

        viewModel.getClassesData().observe(
            viewLifecycleOwner,
            classesObserver
        )
        viewModel.getHomeworkData().observe(
            viewLifecycleOwner,
            homeworkObserver
        )
        viewModel.getClasses()
        viewModel.getHomework()
    }

    private fun renderClassesData(data: ClassesState) = when (data) {
        is ClassesState.Success -> {
            val dataList = data.data
            bind.loadingLayout.visibility = View.GONE
            classesAdapter.setData(dataList)
        }
        is ClassesState.Loading -> {
            bind.loadingLayout.visibility = View.VISIBLE
        }
        is ClassesState.Error -> {
            bind.loadingLayout.visibility = View.GONE

        }
    }

    private fun renderHomeworkData(data: HomeworkState) = when (data) {
        is HomeworkState.Success -> {
            val dataList = data.data
            bind.loadingLayout.visibility = View.GONE
            homeworkAdapter.setData(dataList)
        }
        is HomeworkState.Loading -> {
            bind.loadingLayout.visibility = View.VISIBLE
        }
        is HomeworkState.Error -> {
            bind.loadingLayout.visibility = View.GONE

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }
}