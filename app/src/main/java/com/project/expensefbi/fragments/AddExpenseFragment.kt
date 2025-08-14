package com.project.expensefbi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.project.expensefbi.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddExpenseFragment: Fragment() {

    private var selectedDate: String? = null
    private val PICKER_TITLE_DATE = "Select Date"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.add_expense_layout_fragment, container, false)
        val dateBtn: Button = view.findViewById(R.id.btn_add_date)
        dateBtn.setOnClickListener {
            showDatePicker(dateBtn)
        }
        return view
    }

    private fun showDatePicker(button: Button) {
        // initial selection
        var initialSelection = MaterialDatePicker.todayInUtcMilliseconds()

        if (selectedDate.isNullOrEmpty()){
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = sdf.parse(selectedDate!!)
                if (date != null){
                    val cal = Calendar.getInstance()
                    cal.time = date
                    initialSelection = cal.timeInMillis
                }
            } catch (e: Exception) {
                Log.e("DatePicker", "Error parsing existing date: $selectedDate", e)
            }

            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(PICKER_TITLE_DATE)
                .setSelection(initialSelection)
                .build()

            datePicker.addOnPositiveButtonClickListener {
                selection ->
                val sftDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                selectedDate = sftDate.format(Date(selection))
                Log.d("Date Picker", "Selected Date: $selectedDate")
                button.text = selectedDate
            }
            datePicker.show(parentFragmentManager, "DatePicker")
        }
    }
}