package com.example.asmandroidcobankotlin.uliti

import android.content.Context
import java.util.*

class Uliti {

    // như thằng static
    companion object {
        const val databaseName = "databaseSchool"
        const val databaseVersion = 2
        const val tableName1 = "classroom"
        const val idClassroom = "_idClassroom"
        const val nameClassroom = "_nameClassroom"
        const val history = "_history"

        const val tableName2 = "student"
        const val idStudent = "_idStudent"
        const val nameStudent = "_nameStudent"
        const val birthDayStudent = "_birthDayStudent"

        fun myDate(context: Context) : String{

            val date = Date().time
            val dateFormat = android.text.format.DateFormat.getDateFormat(context)

            return dateFormat.format(date)
        }
    }
}