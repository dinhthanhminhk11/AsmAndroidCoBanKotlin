package com.example.asmandroidcobankotlin.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asmandroidcobankotlin.databinding.ActivityListClassroomBinding
import com.example.asmandroidcobankotlin.model.Classroom
import com.example.asmandroidcobankotlin.view.adapter.ListClassroomAdapter
import com.example.asmandroidcobankotlin.viewmodel.ListClassroomViewModel

class ListClassroomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListClassroomBinding
    private lateinit var viewModel: ListClassroomViewModel
    private lateinit var adapterListClassroom: ListClassroomAdapter

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListClassroomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Danh sách lớp"

        viewModel = ViewModelProvider(this)[ListClassroomViewModel::class.java]
        viewModel.getAll(this)


    }

    override fun onResume() {
        super.onResume()
        getListClassroom()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getListClassroom() = viewModel.classroomList.observe(this, Observer { item ->
        createRecyclerView(item)
    })

    private fun createRecyclerView(listClassroom: List<Classroom>) {
        adapterListClassroom = ListClassroomAdapter(listClassroom)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapterListClassroom
        adapterListClassroom.notifyDataSetChanged()
    }

}