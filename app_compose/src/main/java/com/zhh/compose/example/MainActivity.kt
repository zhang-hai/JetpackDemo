package com.zhh.compose.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.animate.StudyAnimation
import com.zhh.compose.example.canvas.BingDwenDwen
import com.zhh.compose.example.exam01.Conversation
import com.zhh.compose.example.exam01.ExampleData
import com.zhh.compose.example.exam02.MaterialUICompose
import com.zhh.compose.example.gesture.StudyGestureContent
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

enum class MenuType{
    Basic,UI,Draw,Animation,Gesture
}

class MenuItem(var type:MenuType,var name:String)

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
        var menuType = remember{ mutableStateOf(MenuType.Basic)}
        Scaffold(
            topBar = {MyTopBar(menuType)}
        ) {
            when(menuType.value){
                MenuType.Basic->{
                    Conversation(messages = ExampleData.items)
                }
                MenuType.UI->{
                    //2.Material组件和布局
                    MaterialUICompose()
                }
                MenuType.Draw->{
                    BingDwenDwen(modifier = Modifier.fillMaxSize())
                }
                MenuType.Animation->{
                    StudyAnimation()
                }
                MenuType.Gesture->{
                    StudyGestureContent()
                }
            }
        }
    }

    /**
     * 标题栏和菜单
     */
    @Composable
    private fun MyTopBar(menuType: MutableState<MenuType>) {
        val showMenu = remember{ mutableStateOf(false)}
        TopAppBar(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Jetpack Compose",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                //这里加一个Box，可以保证Menu菜单显示和按钮在同一侧
                Box {
                    Image(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "menu",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable { showMenu.value = !showMenu.value },
                        alignment = Alignment.CenterEnd,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    menuList(menuType,showMenu)
                }
            }
        }
    }

    @Composable
    private fun menuList(menuType: MutableState<MenuType>,showMenu: MutableState<Boolean>) {
        val menus = listOf(MenuItem(MenuType.Basic,"快速入门"),
            MenuItem(MenuType.UI,"布局"),
            MenuItem(MenuType.Draw,"Canvas-绘制冰墩墩"),
            MenuItem(MenuType.Animation,"动画"),
            MenuItem(MenuType.Gesture,"手势"))
        DropdownMenu(
            expanded = showMenu.value,
            modifier = Modifier.padding(10.dp),
            onDismissRequest = { showMenu.value = false }
        ) {
            menus.forEachIndexed{index, menuItem ->

                DropdownMenuItem(onClick = {
                    menuType.value = menuItem.type
                    showMenu.value = false
                }) {
                    Text(
                        text = menuItem.name,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = if (menuType.value == menuItem.type) MaterialTheme.colors.primary else Color.Black
                    )
                }
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