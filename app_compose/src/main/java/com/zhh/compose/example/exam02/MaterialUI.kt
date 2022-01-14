package com.zhh.compose.example.exam02

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.R
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

    //Material常用基本组件
    ShowMaterialComponents()

//    1.ScaffoldCompose()

    //2.使用ModalDrawer替代Scaffold中的模态抽屉式导航栏
//        ModalDrawerCompose()

    //3.使用BottomDrawer展示模态抽屉式导航栏,从底部显示
//        BottomDrawerCompose()

    //4.底部动作条
//    BottomSheetCompose()

    //5.背景幕
//    BackdropScaffoldCompose()
}

/**
 * 展示Material基本组件
 *
 * 1.Button : TextButton 、Button、IconButton、IconToggleButton、OutlineButton、
 *            RadioButton、ExtendedFloatingActionButton、FloatingActionButton
 *
 */
@Composable
fun ShowMaterialComponents(){
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            // 文本框
            Text(text = "文本框")
            //1 纯文字按钮
            TextButton(onClick = {

            }) {
                Text("TextButton")
            }
            Spacer(modifier = Modifier.width(5.dp))
            //2 普通按钮
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Button")
            }
            Spacer(modifier = Modifier.width(5.dp))
            // 3 带Icon的按钮
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
            // 4 IconToggleButton
            IconToggleButton(checked = true, onCheckedChange = {},modifier = Modifier.requiredWidth(120.dp)) {
                Text(text = "IconToggleButton")
            }
            Spacer(modifier = Modifier.width(5.dp))
            //5 OutlinedButton
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "OutlinedButton")
            }
            Spacer(modifier = Modifier.width(5.dp))
            // 6 RadioButton
            RadioButton(selected = true, onClick = { /*TODO*/ })
        }

        //扩展悬浮按钮
        ExtendedFloatingActionButton(
            icon={ Icon(imageVector = Icons.Default.Favorite, contentDescription = "")},
            text = { Text("Like")},
            onClick = {  },
        )

        //--------------------分割线
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),color = Color.DarkGray)

        //--------------------Icon
        Icon(painter = rememberVectorPainter(image = Icons.Default.Star), contentDescription = "")
        //--------------------TextField 输入框 未设置onValueChange时，无论输入何内容均显示“你好”，因为输入内容未赋值给value，与Android原生EditText不同
        TextField(
            //输入框中输入的内容
            value = "",
            onValueChange = {},
            //是否单行显示
            singleLine = true,
            // 输入框提示内容
            label = { Text(text = "TextField")},
            //输入框中文字为空时占位内容
            placeholder = { Text(text = "请输入内容")},
            //在输入框左侧显示一个icon
            leadingIcon = {Icon(painter = rememberVectorPainter(image = Icons.Default.Star), contentDescription = "placeholder")},
            //在输入框最右侧显示一个icon
            trailingIcon = {Icon(painter = rememberVectorPainter(image = Icons.Default.Add), contentDescription = "placeholder")},
            //设置输入框输入类型
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        //--------------------OutlinedTextField 输入框,可输入内容并显示
        val valueState = remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = valueState.value,
            onValueChange = {valueState.value = it},
            singleLine = true,
            // 输入框提示内容
            label = { Text(text = "OutlinedTextField")},
            //输入框中文字为空时占位内容
            placeholder = { Text(text = "请输入内容") },
            //在输入框左侧显示一个icon
            leadingIcon = {Icon(painter = rememberVectorPainter(image = Icons.Default.AccountCircle), contentDescription = "placeholder")},
            //在输入框最右侧显示一个icon
            trailingIcon = {Icon(painter = rememberVectorPainter(image = Icons.Default.Add), contentDescription = "placeholder")},
            //设置输入框输入类型
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Row {
            //--------------------Switch 组件
            val switchState = remember {
                mutableStateOf(false)
            }
            Switch(checked = switchState.value, onCheckedChange = {switchState.value = it})

            //--------------------Checkbox 勾选框
            val checkState = remember {
                mutableStateOf(false)
            }
            Checkbox(checked = checkState.value, onCheckedChange = {checkState.value = it})
        }

        //--------------------Slider 滑块
        val sliderState = remember {
            mutableStateOf(0f)
        }
        Slider(value = sliderState.value, onValueChange = {sliderState.value = it})

        //--------------------进度条
        CircularProgressIndicator(progress = 0.5f)
        Spacer(modifier = Modifier.height(10.dp))
        LinearProgressIndicator(0.5f)
        Spacer(modifier = Modifier.height(10.dp))

        //--------------------选项卡 TabRow
        val state = remember { mutableStateOf(0) }
        val titles = listOf("消息", "通讯录", "附近","我")
        TabRow(selectedTabIndex = state.value,modifier = Modifier.height(50.dp)) {
            titles.forEachIndexed {index,text->
                Tab(selected = state.value == index, onClick = { state.value = index }) {
                    VerticalImageText(text = text)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        //--------------------可滑动选项卡 TabRow
        val sTabState = remember { mutableStateOf(0) }
        val sTitles = listOf("消息", "通讯录", "附近","我","交友","教育","购物")
        ScrollableTabRow(selectedTabIndex = sTabState.value,
            modifier = Modifier.height(50.dp),
            edgePadding = 0.dp) {
            sTitles.forEachIndexed {index,text->
                Tab(selected = sTabState.value == index, onClick = { sTabState.value = index }) {
                    VerticalImageText(text = text)
                }
            }
        }
    }
}

/**
 * 采用Scaffold槽位
 */
@Composable
fun ScaffoldCompose(){
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
        ShowMaterialComponents()
    }
}

/**
 * 使用ModalDrawer展示模态抽屉式导航栏
 */
@Composable
fun ModalDrawerCompose(){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {DrawerContent()}
    ) {
        TextButton(
            modifier = Modifier.padding(top = 200.dp),
            onClick = {
            scope.launch {
                //控制抽屉显示与关闭
                drawerState.apply {
                    if (isOpen) close() else open()
                }
            }
        }) {
            Text(text = "ModalDrawer抽屉")
        }
    }
}

/**
 * 使用BottomDrawer展示模态抽屉式导航栏,从底部显示
 * 注：在当前版本中，该功能为实验性的功能
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomDrawerCompose(){
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()
    BottomDrawer(
        drawerState = drawerState,
        drawerContent = {DrawerContent()}
    ) {
        TextButton(
            modifier = Modifier.padding(top = 300.dp),
            onClick = {
            scope.launch {
                //控制抽屉显示与关闭
                drawerState.apply {
                    if (isOpen) close() else open()
                }
            }
        }) {
            Text(text = "底部显示抽屉")
        }
    }
}

/**
 * 底部动作条，
 * 注：在当前版本中，该功能为实验性的功能
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetCompose(){
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = { MySheetContent()},
        sheetPeekHeight = 120.dp,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //点击折叠和收起
                      scope.launch {
                          scaffoldState.bottomSheetState.apply {
                              if (isCollapsed) expand() else collapse()
                          }
                      }
                },
                //设置悬浮窗背景色
                backgroundColor = Color.White
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_directions_bike_24),
                    contentDescription = "",
                    //过滤颜色
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
            }
        }
    ) {
        Text(text = "应用主内容")
    }
}

@Composable
fun MySheetContent(){
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape
    ) {
        Column (modifier = Modifier.padding(10.dp)){
            Text(
                text = "Corner Cafe",
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = Color.White
            )
            Row {
                Text(
                    text = "Coffee shop",
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "25 min",
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    color = Color.White
                )
            }
        }
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        VerticalImageText(rememberVectorPainter(Icons.Default.Call),text = "Call")
        VerticalImageText(rememberVectorPainter(Icons.Default.Warning),text = "Website")
        VerticalImageText(rememberVectorPainter(Icons.Default.Send),text = "Save")
    }

    Divider(modifier = Modifier.fillMaxWidth(),color = Color.Gray)
    ImageText(painter = rememberVectorPainter(Icons.Default.LocationOn),text = "3794 Pretty View Lane")
    ImageText(painter = rememberVectorPainter(Icons.Default.Lock),text = "3794 Pretty View Lane")
    ImageText(painter = rememberVectorPainter(Icons.Default.Share),text = "Share")
}


/**
 * 背景幕 - BackdropScaffold
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackdropScaffoldCompose(){
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = { TopBar(
            onMenuClick = {
                scope.launch {
                    scaffoldState.apply {
                        //若隐藏了，则显示，反之则隐藏
                        if (isConcealed) reveal() else conceal()
                    }
                }
            }
        ) },
        backLayerContent = { ShowMaterialComponents() },
        frontLayerContent ={ ShowMaterialComponents() },
        //后层header的可视高度高度
        peekHeight = 50.dp,
        //前层header的最小非活动高度
        headerHeight = 160.dp,
        // 前层可滑动
        gesturesEnabled = true
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