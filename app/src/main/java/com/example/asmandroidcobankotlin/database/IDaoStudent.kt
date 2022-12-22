package com.example.asmandroidcobankotlin.database

import com.example.asmandroidcobankotlin.model.Student

interface IDaoStudent {
    fun insertStudent(student: Student)
    fun getAllDataStudent(): List<Student>
    fun getStudentById(id: Int): Student
    fun deleteStudentById(student: Student)
    fun updateStudent(student: Student)
}