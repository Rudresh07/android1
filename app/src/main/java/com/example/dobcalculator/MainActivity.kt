package com.example.dobcalculator

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dateselected : TextView? = null
    var minutes : TextView? = null
    //we leave this empty untill we didnt assign a value to it i.e after picking date
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 : Button = findViewById(R.id.button1)

        dateselected = findViewById(R.id.textView5)
        button1.setOnClickListener {
            clickdatepicker()
        }
        minutes = findViewById(R.id.textView7)
    }


   private fun clickdatepicker()
    {
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)

        val dpd =   DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_,Selectedyear,Selectedmonth,dayofMonth ->

                val selectedDate = "$dayofMonth/${Selectedmonth+1}/${Selectedyear}"

                dateselected?.text = selectedDate


                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                //the patter is specific never change in kotlin
                //here we import simple date format from java


                val thedate = sdf.parse(selectedDate)

                    //.let is approach to make sure if date is empty than code doesn,t execute
                thedate?.let{

                    val selecteddateinmin = thedate.time / 60000 //time is same as get time()

                    val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    //current date in millisecond since 1 january of the same year

                    currentdate?.let{
                        val currentdateinmin = currentdate.time/ 60000
                        val difference = currentdateinmin - selecteddateinmin
                        val totalmin = "${difference / 60}"
                        minutes?.text = totalmin
                    }

                }

            },
        year,
        month,
        day
        )
       dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()



    }
}