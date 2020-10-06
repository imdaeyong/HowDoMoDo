package com.ssafy.howdomodo.ui.last

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.ui.BasicActivity
import kotlinx.android.synthetic.main.activity_last.*

class LastActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
        actFinish()
        act_last_cl_next.setOnClickListener {
            finish()
        }
    }
}