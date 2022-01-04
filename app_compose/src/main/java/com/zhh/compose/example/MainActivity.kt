package com.zhh.compose.example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.exam01.IntroductionActivity
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
        Row(modifier = Modifier.fillMaxSize().padding(10.dp)
            ,horizontalArrangement= Arrangement.Center) {
           Button(modifier = Modifier.size(Dp.Infinity,Dp.Infinity),onClick = {
               startActivity(Intent(this@MainActivity,IntroductionActivity::class.java))
           }) {
               Text(text = "Compose快速入门")
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