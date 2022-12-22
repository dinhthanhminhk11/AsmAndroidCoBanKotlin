package com.example.asmandroidcobankotlin

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asmandroidcobankotlin.databinding.ActivityMainBinding
import com.example.asmandroidcobankotlin.model.ButtonCustom
import com.example.asmandroidcobankotlin.model.Classroom
import com.example.asmandroidcobankotlin.uliti.Uliti.Companion.myDate
import com.example.asmandroidcobankotlin.view.ListClassroomActivity
import com.example.asmandroidcobankotlin.view.ManagerStudentActivity
import com.example.asmandroidcobankotlin.view.adapter.ButtonAdapter
import com.example.asmandroidcobankotlin.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), ButtonAdapter.Callback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listButton: List<ButtonCustom>
    private lateinit var adapterButton: ButtonAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadLanguage()

        adapterButton = ButtonAdapter(this, listButton)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapterButton

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun loadLanguage() {
        listButton = listOf(
            ButtonCustom(1, "Thêm lớp", Color.WHITE),
            ButtonCustom(2, "Xem danh sách lớp", Color.WHITE),
            ButtonCustom(3, "Quản lí sinh viên", Color.WHITE)
        )
    }

    override fun onCLick(id: Int) {
        when (id) {
            1 -> {
                showDialog()
            }
            2 -> {
                startActivity(Intent(this@MainActivity, ListClassroomActivity::class.java))
            }
            3 -> {
                startActivity(Intent(this@MainActivity, ManagerStudentActivity::class.java))
            }
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_layout)

        val idClassroom: EditText by lazy { dialog.findViewById<EditText>(R.id.idClassroom) }
        val nameClassroom: EditText by lazy { dialog.findViewById<EditText>(R.id.nameClassroom) }
        val clearText: Button by lazy { dialog.findViewById<Button>(R.id.clear_text) }
        val saveClassroom: Button by lazy { dialog.findViewById<Button>(R.id.saveClassroom) }

        clearText.setOnClickListener {
            idClassroom.setText("")
            nameClassroom.setText("")
            dialog.dismiss()
        }
        saveClassroom.setOnClickListener {
            var idClassroomText: String = idClassroom.text.toString().trim()
            var nameClassroomText: String = nameClassroom.text.toString().trim()
            val historyText: String = myDate(this)

            if (idClassroomText != "" && nameClassroomText != "") {
                saveClassroom(Classroom(idClassroomText, nameClassroomText, historyText))
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Thêm bại", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun saveClassroom(classroom: Classroom) {
        viewModel.saveClassroom(classroom, this);
    }
}