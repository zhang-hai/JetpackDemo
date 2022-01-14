package com.zhh.compose.example.exam02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

class CustomUIActivity:ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackDemoTheme {
                //ItemView(text = "这是一个数据")
                //基于槽位开发
//                ScaffoldComposable{
//                    finish()
//                }
                //2.Material组件和布局
//                MaterialUICompose()

                //3.ConstraintLayout
                StudyConstraintLayout()
            }
        }
    }


    @Preview
    @Composable
    fun PreviewContent(){
        JetpackDemoTheme {
            ItemView(text = "这是一个数据")
        }
    }
}