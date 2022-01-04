package com.zhh.compose.example.exam01

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.bean.Message

/**
 * 参数为数据集
 */
@Composable
fun Conversation(messages:List<Message>){
    LazyColumn {
        items(messages){ message->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    //行
    Row (modifier = Modifier.padding(horizontal = 5.dp,vertical = 5.dp)){
        //添加一个图片
        Image(
            //填充内容
            painter = painterResource(id = message.iconId),
            contentDescription = "logo",
            //尺寸及形状
            modifier= Modifier
                .padding(top = 2.dp)
                .size(40.dp)             //图像尺寸
                .clip(CircleShape)       //形状
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)//边框样式
        )
        //占空 间距
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {
            mutableStateOf(false)
        }

        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        //列
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondaryVariant,
                //添加MaterialTheme排版
                style = MaterialTheme.typography.subtitle2
            )
            //添加一个行间距
            Spacer(modifier = Modifier.height(4.dp))
            //设置一个边框
            Surface(shape = MaterialTheme.shapes.medium,
                elevation = 2.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize()
                    .padding(1.dp)
                    .clickable { isExpanded = !isExpanded }
            ){
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines=if (isExpanded) Int.MAX_VALUE else 1,
                    //添加MaterialTheme排版
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}