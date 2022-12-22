package com.example.asmandroidcobankotlin.database

import com.example.asmandroidcobankotlin.model.Classroom

interface IDaoClassroom {
    fun insert(classroom: Classroom): Boolean
    fun getAllData(): List<Classroom>
    fun getClassroomById(id: Int): Classroom
    fun deleteClassroomById(classroom: Classroom): Boolean
    fun updateClassroom(classroom: Classroom): Boolean
}