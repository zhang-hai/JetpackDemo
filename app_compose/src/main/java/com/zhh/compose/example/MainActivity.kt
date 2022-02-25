package com.zhh.compose.example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.animate.StudyAnimationActivity
import com.zhh.compose.example.canvas.StudyCanvasActivity
import com.zhh.compose.example.exam01.IntroductionActivity
import com.zhh.compose.example.exam02.CustomUIActivity
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

class MainActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackDemoTheme {
               MainContent()
            }
        }
    }


    @Composable
    fun MainContent(){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            horizontalAlignment= Alignment.CenterHorizontally) {
            Button(modifier = Modifier.size(200.dp,Dp.Infinity),onClick = {
               startActivity(Intent(this@MainActivity,IntroductionActivity::class.java))
            }) {
               Text(text = "Compose快速入门")
            }
            Spacer(modifier = Modifier.size(1.dp,10.dp))
            Button(modifier = Modifier.size(200.dp,Dp.Infinity),onClick = {
                startActivity(Intent(this@MainActivity,CustomUIActivity::class.java))
            }) {
                Text(text = "布局")
            }
            Spacer(modifier = Modifier.size(1.dp,10.dp))
            Button(modifier = Modifier.size(200.dp,Dp.Infinity),onClick = {
                startActivity(Intent(this@MainActivity,StudyCanvasActivity::class.java))
            }) {
                Text(text = "Canvas-绘制冰墩墩")
            }
            Spacer(modifier = Modifier.size(1.dp,10.dp))
            Button(modifier = Modifier.size(200.dp,Dp.Infinity),onClick = {
                startActivity(Intent(this@MainActivity,StudyAnimationActivity::class.java))
            }) {
                Text(text = "动画")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMainContent(){
        JetpackDemoTheme {
            MainContent()
        }
    }
}