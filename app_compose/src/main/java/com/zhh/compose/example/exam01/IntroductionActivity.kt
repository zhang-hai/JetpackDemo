package com.zhh.compose.example.exam01

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zhh.compose.example.R
import com.zhh.compose.example.bean.Message
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

class IntroductionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //主题
            JetpackDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Conversation(messages = ExampleData.items)
                }
            }
        }
    }
}



@Preview(name = "Light Mode")//白天模式
@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_YES,name = "Dark Mode")//夜间模式
@Composable
fun DefaultPreview() {
    JetpackDemoTheme {
        MessageCard(Message(R.mipmap.ic_girl,"Android","Jetpack Compose"))
    }
}