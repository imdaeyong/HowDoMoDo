package com.ssafy.howdomodo.di

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.howdomodo.R
import org.koin.android.ext.android.inject

class DiActivity : AppCompatActivity() {

    private val schoolService : SchoolService by inject()
    private val studentController : StudentController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        schoolService.moveSchool("청구중학교")
        studentController.print()
    }
}