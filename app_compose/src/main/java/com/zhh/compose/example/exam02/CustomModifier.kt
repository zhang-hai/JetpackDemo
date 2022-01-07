package com.zhh.compose.example.exam02

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.ui.theme.JetpackDemoTheme

/**
 * 自定义布局-修改文字基线距离顶部距离
 */
fun Modifier.firstBaselineToTop(firstBaselineToTop:Dp)=layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseLine = placeable[FirstBaseline]
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseLine
    val height = placeable.height + placeableY
    layout(placeable.width,height){
        placeable.placeRelative(0,placeableY)
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