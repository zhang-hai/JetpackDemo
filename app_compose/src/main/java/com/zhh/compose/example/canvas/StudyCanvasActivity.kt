package com.zhh.compose.example.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.zhh.compose.example.BingDwenDwen
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

class StudyCanvasActivity :ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackDemoTheme {
                BingDwenDwen(modifier = Modifier.fillMaxSize())
            }
        }
    }

}