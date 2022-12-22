package com.example.asmandroidcobankotlin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.asmandroidcobankotlin.database.MyDatabaseHelper
import com.example.asmandroidcobankotlin.model.Classroom

class MainViewModel : ViewModel() {
    fun saveClassroom(classroom: Classroom, context: Context) {
        val myDatabaseHelper: MyDatabaseHelper = MyDatabaseHelper(context)

        try {
            myDatabaseHelper.insert(classroom)
        }catch (exception: Exception){
            println(exception.localizedMessage)
        }
    }
}