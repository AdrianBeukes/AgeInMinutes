package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_datePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->

                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                val simpledateformat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = simpledateformat.parse(selectedDate)
                val currentDate = simpledateformat.parse(simpledateformat.format(System.currentTimeMillis()))

                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                txt_selectedDate.text = selectedDate
                txt_minutes.text = differenceInMinutes.toString()

                txt_selectedDateSub.visibility = View.VISIBLE
                txt_minutesSub.visibility = View.VISIBLE
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = (Date().time - 86400000)
        datePickerDialog.show()
    }
}