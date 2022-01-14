package com.zhh.compose.example.exam02

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 通过自定义布局实现一个流式布局
 *
 * @param modifier 修饰符
 * @param horizontalSpace 水平方向组件之间的距离
 * @param verticalSpace 垂直方向上组件的间距
 * @param content 子组件
 */
@Composable
fun StaggeredGridLayout(
    modifier: Modifier = Modifier,
    horizontalSpace:Dp = 0.dp,
    verticalSpace:Dp = 0.dp,
    content:@Composable ()->Unit
){
    Layout(content = content,modifier = modifier){measurables,constraints ->
        //测量
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        //布局
        layout(constraints.maxWidth,constraints.maxHeight){
            var yPosition = 0
            var xPosition = 0

            placeables.forEach {placeable ->
                if (xPosition + placeable.width > constraints.maxWidth){
                    //换到下一行
                    yPosition += placeable.height + verticalSpace.toPx().toInt()
                    //xPosition重新从0开始
                    xPosition = 0
                }
                placeable.placeRelative(xPosition,yPosition)
                //下一个content的开始位置
                xPosition += placeable.width + horizontalSpace.toPx().toInt()
            }

        }
    }
}


@Composable
fun ItemView(modifier: Modifier = Modifier,text:String){
    Card(
        modifier = Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colors.onSecondary,
            shape = RoundedCornerShape(5.dp)
        )
    ) {
        Text(modifier = Modifier.padding(horizontal = 10.dp,vertical = 5.dp),text = text)
    }
}

@Preview
@Composable
fun PreviewStaggeredGridLayout(){
    val list = listOf("Like","I like","瀑布","九寨沟","北京-上海机票","北京-上海机票","北京-上海机票",
        "北京-上海机票","九寨沟","I like","北京-上海机票","火车票","火车票")
    StaggeredGridLayout(
        modifier = Modifier.padding(5.dp),
        horizontalSpace = 10.dp,
        verticalSpace = 10.dp
    ){
        list.forEach {
            ItemView(text = it)
        }
    }
}