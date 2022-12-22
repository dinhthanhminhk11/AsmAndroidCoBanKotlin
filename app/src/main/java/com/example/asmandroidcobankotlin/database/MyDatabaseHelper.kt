package com.example.asmandroidcobankotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.asmandroidcobankotlin.model.Classroom
import com.example.asmandroidcobankotlin.model.Student
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.birthDayStudent
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.databaseName
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.databaseVersion
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.history
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.idClassroom
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.idStudent
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.nameClassroom
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.nameStudent
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.tableName1
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.tableName2


class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, databaseName, null, databaseVersion), IDaoClassroom, IDaoStudent {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql: String = "CREATE TABLE $tableName1 (" +
                "$idClassroom TEXT PRIMARY KEY ," +
                "$nameClassroom TEXT , " +
                "$history TEXT " +
                ")"
        db?.execSQL(sql)

        val sql2: String = "CREATE TABLE $tableName2 (" +
                "$idStudent TEXT PRIMARY KEY ," +
                "$nameStudent TEXT," +
                "$birthDayStudent TEXT ," +
                "$idClassroom TEXT REFERENCES $tableName1 ($idClassroom)" +
                ")"
        db?.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    override fun insert(classroom: Classroom): Boolean {
        val sqLiteDatabase: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(idClassroom, classroom.id)
        contentValues.put(nameClassroom, classroom.nameClass)
        contentValues.put(history, classroom.historyCreate)

        val sonuc = sqLiteDatabase.insertOrThrow(tableName1, null, contentValues)
        sqLiteDatabase.close()

        return sonuc > 0
    }

    override fun getAllData(): List<Classroom> {
        val sqLiteDatabase: SQLiteDatabase = this.readableDatabase
        val list1 = ArrayList<Classroom>()
        val sql_read = "select * from $tableName1 ORDER BY $idClassroom DESC"

        val cursor = sqLiteDatabase.rawQuery(sql_read, null)

        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(0)
                val name = cursor.getString(1)
                val history = cursor.getString(2)

                val classroom: Classroom = Classroom(id, name, history)
                list1.add(classroom)
            }
            cursor.close()
        } else {
            println("Looxi")
        }
        sqLiteDatabase.close()
        return list1
    }

    override fun getClassroomById(id: Int): Classroom {
        val sqLiteDatabase: SQLiteDatabase = this.readableDatabase

        val sql_read = "select * from $tableName1 where $idClassroom = $id"

        val cursor = sqLiteDatabase.rawQuery(sql_read, null)

        var id: String? = null
        var name: String? = null
        var history: String? = null

        while (cursor.moveToNext()) {
            id = cursor.getString(0)
            name = cursor.getString(1)
            history = cursor.getString(2)
        }

        cursor.close()
        sqLiteDatabase.close()
        return Classroom(id!!, name!!, history!!)
    }

    override fun deleteClassroomById(classroom: Classroom): Boolean {
        val sqliDatabase = this.writableDatabase

        val c = sqliDatabase.delete(
            tableName1,
            "$idClassroom = ? ",
            arrayOf<String>(classroom.id.toString())
        )
        sqliDatabase.close()
        return c > 0;
    }

    override fun updateClassroom(classroom: Classroom): Boolean {
        val sqLiteDatabase = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(nameClassroom, classroom.nameClass)
        contentValues.put(history, classroom.historyCreate)

        val c = sqLiteDatabase.update(
            tableName1,
            contentValues,
            "$idClassroom = ? ",
            arrayOf(classroom.id)
        )
        sqLiteDatabase.close()

        return c > 0
    }

    override fun insertStudent(student: Student) {
        TODO("Not yet implemented")
    }

    override fun getAllDataStudent(): List<Student> {
        TODO("Not yet implemented")
    }

    override fun getStudentById(id: Int): Student {
        TODO("Not yet implemented")
    }

    override fun deleteStudentById(student: Student) {
        TODO("Not yet implemented")
    }

    override fun updateStudent(student: Student) {
        TODO("Not yet implemented")
    }
}