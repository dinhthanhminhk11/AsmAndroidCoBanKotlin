package com.example.asmandroidcobankotlin.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asmandroidcobankotlin.database.MyDatabaseHelper
import com.example.asmandroidcobankotlin.model.Classroom
import java.lang.Exception

class ListClassroomViewModel : ViewModel() {
     val classroomList = MutableLiveData<List<Classroom>>()

    fun getAll(context: Context) {
        val myDatabaseHelper: MyDatabaseHelper = MyDatabaseHelper(context)

        try {
            classroomList.value = myDatabaseHelper.getAllData()
        } catch (error: Exception) {
            println(error.localizedMessage)
        }
    }
}