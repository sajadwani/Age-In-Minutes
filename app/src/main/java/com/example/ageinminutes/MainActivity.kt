package com.example.ageinminutes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ageinminutes.databinding.ActivityMainBinding
import android.app.DatePickerDialog
import java.text.ParseException
import java.util.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener {
            openDatePicker()
        }
    }

     // this function Opens Date Picker
    @SuppressLint("SimpleDateFormat")
    private fun openDatePicker() {
       val calendar = Calendar.getInstance()
       val  year = calendar.get(Calendar.YEAR)
       val  month = calendar.get(Calendar.MONTH)
       val  dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
       DatePickerDialog(this, {
          _, selectedYear, selectedMonth, selectedDayOfMonth ->
           val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
           binding.tvDate.text = selectedDate
           val sdf  = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
           try {
               val date = sdf.parse(selectedDate)
               val selectedDateInMinutes = date?.time?.div(60000)
               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
               val currentDateInMinutes = currentDate?.time?.div(60000)
               val differenceInMinutes = (currentDateInMinutes?.minus(selectedDateInMinutes!!))
               binding.tvSelectedDateInMinutes.text = differenceInMinutes.toString()
           } catch (e: ParseException) {
               e.printStackTrace()
           }
       },
           year,
           month,
           dayOfMonth).show()
    }

}