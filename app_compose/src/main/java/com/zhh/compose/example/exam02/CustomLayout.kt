package com.zhh.compose.example.exam02

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

/**
 * 自定义布局
 *
 * 1.扩展布局修饰符
 *
 * 2.创建自定义布局
 *
 * 3.布局方向
 */

/**
 * 1.自定义布局修饰符-修改文字基线距离顶部距离
 */
fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp)=layout { measurable, constraints ->
    //先测量
    val placeable = measurable.measure(constraints)
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseLine = placeable[FirstBaseline]
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseLine
    val height = placeable.height + placeableY
    //重新布局
    layout(placeable.width,height){
        placeable.placeRelative(0,placeableY)
    }
}

/**
 * 2.自定义Layout实现组件垂直布局
 */
@Composable
fun MyBasicColumn(
    modifier: Modifier = Modifier,
    content:@Composable ()->Unit
){
    //步骤一：
    //其中measurables,constraints为Layout最后一个参数的lambda写法
    Layout(content = content, modifier = modifier){ measurables,constraints ->

        //步骤二：通过给点的约束条件constraints对组件进行测量
        val placeables = measurables.map {
            it.measure(constraints)
        }
        //获取所有元素的高度总和
        val wrapHeight = placeables.sumOf {
            it.height
        }
        //步骤三：布局，设置可允许的布局大小
        layout(constraints.maxWidth,wrapHeight){

            var yPosition = 0
            //步骤四：设置每个组件的位置
            placeables.forEach {placeable->
                //设置组件的x,y坐标
                placeable.placeRelative(x = 0,y=yPosition)
                //计算下一个组件的y坐标
                yPosition += placeable.height
            }

        }
    }
}

/**
 * 3.布局方向
 */
@Composable
fun ChangeColumnDirection(){
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
        Column(Modifier.fillMaxWidth()) {
            Text("Title")
            Text("Subtitle")
        }
    }
}


@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    JetpackDemoTheme {
        //使用基线到顶部间距
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    JetpackDemoTheme {
        //直接使用padding
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}

@Preview
@Composable
fun CustomLayoutPreview() {
    JetpackDemoTheme {
        MyBasicColumn(
            modifier = Modifier.wrapContentHeight()
        ) {
            //直接使用padding
            Text("Hi there! - 1")
            //直接使用padding
            Text("Hi there! - 2")
            //直接使用padding
            Text("Hi there! - 3")
            //直接使用padding
            Text("Hi there! - 4")
            //直接使用padding
            Text("Hi there! - 5")
        }

    }
}

@Preview
@Composable
fun ChangeColumnDirectionPreview() {
    JetpackDemoTheme {
        ChangeColumnDirection()
    }
}