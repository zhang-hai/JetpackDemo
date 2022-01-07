package com.zhh.compose.example.exam02

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.R
import com.zhh.compose.example.bean.Message
import com.zhh.compose.example.exam01.MessageCard
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

/**
 * 布局的基础知识
 *
 *  修饰符
 *      修饰符有顺序区分
 *      内置修饰符
 *      Compose类型安全
 *  可滚动布局
 *  自适应布局
 *      约束条件
 *  基于槽位的布局-Scaffold、TopAppBar
 */


/**
 * Compose 中的类型安全
 * 1.Box中的matchParentSize,仅在BoxScope作用域内使用，即针对Box使用
 * 2.Row 和 Column 中的 weight
 */
@Composable
fun MatchParentSizeComposable(){
    Column {
        //1.Box中的matchParentSize,仅在BoxScope作用域内使用，即针对Box使用
        Text(
            text = "1.Box中的matchParentSize,仅在BoxScope作用域内使用，即针对Box使用",
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
        Box (modifier = Modifier.size(width = 230.dp,height = 100.dp)){
            Spacer(
                Modifier
                    .matchParentSize()
                    .background(Color.LightGray))
            MessageCard(message = Message(R.mipmap.ic_girl,"John","This is a message."))
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "2.Row 和 Column 中的 weight",
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
        //2.Row 和 Column 中的 weight
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = R.mipmap.ic_girl),
                contentDescription = "小女孩",
                modifier = Modifier
                    .background(color = Color.Gray)
                    .size(40.dp, 40.dp)
                    .clip(CircleShape)//此时设置的圆形已经不生效了
                    .weight(1f)
            )
            Column(modifier = Modifier.weight(2f)) {
                Text(text = "John")
                Text(text = "This is a message.")
            }
        }
    }
}

/**
 * 自适应布局
 */
@Composable
fun WithConstraintsComposable(name:String="John"){
    BoxWithConstraints {
        Text("Hello,$name")
    }
}

/**
 * 基于槽位的布局
 *
 */
@Composable
fun ScaffoldComposable(onBackClick:()->Unit){
    Scaffold(
        //标题栏
        topBar = { TopBar(onBackClick) },
        //浮动按钮
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Image(
                    rememberVectorPainter(image = Icons.Default.Add),
                    contentDescription = "邮件",
                    contentScale = ContentScale.Inside,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        },
        //底部标题栏
        bottomBar = { BottomBar() },
        //抽屉
        drawerContent = {
            DrawerContent()
        }
    ) {
        Image(painter = painterResource(id = R.mipmap.ic_girl), contentDescription = "内容")

    }
}

@Composable
fun DrawerContent(){
    ImageText(text = "今天还没有打卡哦")
    MessageCard(message = Message(R.mipmap.ic_girl,"姜雨柔","4102319303@qq.com"))
    Spacer(modifier = Modifier.height(20.dp))
    ImageText(rememberVectorPainter(Icons.Default.Phone),"直播")
    ImageText(rememberVectorPainter(Icons.Sharp.Favorite),"点我开通会员")
    ImageText(rememberVectorPainter(Icons.Rounded.Face),"我的QQ钱包")
    ImageText(rememberVectorPainter(Icons.Default.Favorite),"限免装扮大放送")
    ImageText(rememberVectorPainter(Icons.Default.Share),"我的情侣空间")
    ImageText(rememberVectorPainter(Icons.Default.Star),"免流量玩手Q")
    ImageText(rememberVectorPainter(Icons.Default.Favorite),"我的收藏")
    ImageText(rememberVectorPainter(Icons.Default.Place),"我的相册")
    ImageText(rememberVectorPainter(Icons.Default.Refresh),"我的文件")
}

@Composable
fun TopBar(onBackClick:()->Unit = {},onMenuClick:()->Unit = {}){
    TopAppBar {
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "返回按钮",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(40.dp)
                    .animateContentSize()
                    .clickable {onBackClick() }
            )
            Text("这里是标题",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Image(rememberVectorPainter(Icons.Default.Menu),
                contentDescription = "菜单",
                colorFilter = ColorFilter.tint(Color.White),
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(40.dp)
                    .animateContentSize()
                    .clickable { onMenuClick()}
            )
        }
    }
}

@Composable
fun BottomBar(){
    BottomNavigation {
        VerticalImageText(rememberVectorPainter(Icons.Rounded.Email),"消息")
        VerticalImageText(rememberVectorPainter(Icons.Rounded.AccountBox),"联系人")
        VerticalImageText(rememberVectorPainter(Icons.Rounded.Favorite),"看点")
        VerticalImageText(rememberVectorPainter(Icons.Rounded.Menu),"动态")
    }
}

/**
 * 自定义带图标的文字
 */
@Composable
fun ImageText(painter: Painter?=null,text:String){
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .animateContentSize()
            .clickable { }
    ){
        if (painter != null){
            Image(painter = painter, contentDescription = "logo")
        }
       Spacer(modifier = Modifier.width(10.dp))
       Text(text = text)
    }
}

@Composable
fun VerticalImageText(painter: Painter?=null,text:String){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .defaultMinSize(minWidth = 50.dp)
    ){
        if (painter != null){
            Image(painter = painter, contentDescription = "logo")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text)
    }
}

@Preview
@Composable
fun BasicUIPreview() {
    JetpackDemoTheme {
        //Compose 中的类型安全
//        MatchParentSizeComposable()
        //自适应布局
//        WithConstraintsComposable("姜雨柔")
        //基于槽位的布局
        ScaffoldComposable({})
    }
}
