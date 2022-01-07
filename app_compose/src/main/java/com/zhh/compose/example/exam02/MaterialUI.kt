package com.zhh.compose.example.exam02

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.ui.theme.JetpackDemoTheme
import kotlinx.coroutines.launch

/**
 * 本例讲述Material组件和布局
 *
 * 内容槽
 * Button
 * Scaffold
 *      屏幕内容
 *      应用栏
 *      悬浮操作按钮
 *      信息提示控件
 *      抽屉式导航栏
 * 模态抽屉式导航栏
 * 底部动作条
 * 背景幕
 *
 */

@Composable
fun MaterialUICompose(){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        //顶部工具栏
        topBar = { TopBar(onMenuClick = {
            scope.launch {
                //控制抽屉显示与关闭
                scaffoldState.drawerState.apply {
                    if (isOpen) close() else open()
                }
            }
        })},
        //底部工具栏
        bottomBar = {
                    BottomAppBar(
                        //当floatActionButton与bottomBar重叠时剪切样式
                        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(50.dp)),

                    ) {
                        VerticalImageText(rememberVectorPainter(Icons.Rounded.Email),"消息")

                        VerticalImageText(rememberVectorPainter(Icons.Rounded.Menu),"动态")
                    }
        },
        //floatingActionButton位置
        floatingActionButtonPosition = FabPosition.Center,//居中
        //添加一个floatingActionButton
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //点击按钮，显示一个SnackBar
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("你点击了FloatingActionButton",actionLabel = "Done")
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
           },
        //是否与bottomBar区域重叠,true叠加，false 不叠加
        isFloatingActionButtonDocked = true,
        drawerContent = {DrawerContent()}
    ) {
        ShowButtons()
    }
}

@Composable
fun ShowButtons(){
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())) {
            TextButton(onClick = {  }) {
                Text("TextButton")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Button")
            }
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(onClick = { /*TODO*/ },modifier = Modifier.requiredWidth(120.dp)) {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = "IconButton")
                }
            }
        }
        Row {
            Spacer(modifier = Modifier.width(5.dp))
            IconToggleButton(checked = true, onCheckedChange = {},modifier = Modifier.requiredWidth(120.dp)) {
                Text(text = "IconToggleButton")
            }
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "OutlinedButton")
            }
            Spacer(modifier = Modifier.width(5.dp))
            RadioButton(selected = true, onClick = { /*TODO*/ })
        }

        ExtendedFloatingActionButton(
            icon={ Icon(imageVector = Icons.Default.Favorite, contentDescription = "")},
            text = { Text("Like")},
            onClick = {  },
        )
    }
}

@Composable
fun ModalDrawerCompose(){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {DrawerContent()}
    ) {

    }
}

@Preview
@Composable
fun MaterialUIPreview() {
    JetpackDemoTheme {
        //Material组件和布局
        MaterialUICompose()
    }
}