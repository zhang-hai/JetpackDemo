package com.zhh.compose.example.animate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

class StudyAnimationActivity :ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackDemoTheme {
                StudyAnimation()
            }
        }
    }

}