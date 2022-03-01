package com.zhh.compose.example.gesture

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


/**
 * Jetpack Compose 手势学习
 *
 * 1.点击
 * 2.滚动
 * 3.拖动
 * 4.滑动
 * 5.多点触控
 *
 */
@Composable
fun StudyGestureContent(){
    Column(modifier = Modifier.fillMaxSize()) {

        GestureOfClick()

        Spacer(modifier = Modifier.height(10.dp))
        
        GestureOfScroll()

        Spacer(modifier = Modifier.height(10.dp))

        GestureOfDrag()

        Spacer(modifier = Modifier.height(10.dp))

        GestureOfSwipe()

        Spacer(modifier = Modifier.height(10.dp))

        GestureOfMultiTouch()
    }
}

/**
 * 手势 - 点击
 */
@Composable
private fun GestureOfClick(){
    var colorState by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .size(60.dp)
        .background(color = if (colorState) Color.LightGray else Color.Gray)
        .clickable { colorState = !colorState },
        contentAlignment = Alignment.Center
    ){
        Text(text = "点击")
    }
}

/**
 * 手势 - 滚动
 * 1.滚动修饰符
 * 2.可滚动的修饰符
 * 3.嵌套滚动
 */
@Composable
private fun GestureOfScroll(){
    Row(modifier = Modifier.fillMaxWidth()) {
        // 方式一：使用滚动修饰符
        val scrollState = rememberScrollState()
        Box(modifier = Modifier
            .size(60.dp)
            .background(color = Color.LightGray)
            .verticalScroll(scrollState),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "滚动",
                color = Color.White,
                modifier = Modifier
                    .height(120.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .background(color = Color.Blue)
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        //方式二：可滚动的修饰符 检测到滚动，但不会滚动，需与ScrollableController配合实现滚动
        var offset by remember { mutableStateOf(0f) }
        Box(
            modifier = Modifier
                .size(120.dp, 60.dp)
                .scrollable(
                    state = rememberScrollableState { delta ->
                        offset += delta
                        delta
                    },
                    orientation = Orientation.Vertical
                )
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = offset.toString())
        }

        Spacer(modifier = Modifier.width(5.dp))

        //方式三、嵌套滚动，当内部不可滚动时会自动切到父布局的滚动
        val gradient = Brush.verticalGradient(0f to Color.Gray, 1000f to Color.White)
        Box(
            modifier = Modifier
                .size(120.dp, 80.dp)
                .background(color = Color.LightGray)
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                repeat(5){
                    //内部嵌套滚动
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(color = Color.LightGray)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            "Scroll here",
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(12.dp, Color.DarkGray)
                                .background(brush = gradient)
                                .padding(top = 20.dp, start = 10.dp)
                                .height(80.dp)
                        )
                    }
                }
            }
        }
    }
}


/**
 * 手势 - 拖动
 * 1.单方向拖动
 * 2.任意位置拖动
 */
@Composable
private fun GestureOfDrag(){
    var offsetX by remember { mutableStateOf(0f) }
    //方式一：单方向（横向或竖向）拖动
    Box(
        modifier = Modifier
            .size(60.dp)
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .draggable(
                state = rememberDraggableState {
                    offsetX += it
                },
                orientation = Orientation.Horizontal
            )
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "拖动")
    }
    var offX by remember { mutableStateOf(0f) }
    var offY by remember { mutableStateOf(0f) }

    //方式二：使用pointerInput修饰符进行设置拖动任意位置
    Box(
        modifier = Modifier
            .size(80.dp, 60.dp)
            .offset { IntOffset(offX.roundToInt(), offY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offX += dragAmount.x
                    offY += dragAmount.y
                }
            }
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ){
        Text(text = "任意位置拖动")
    }
}

/**
 * 手势 - 滑动
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GestureOfSwipe(){
    val width = 150.dp
    val squareSize = 75.dp
    val sizePx = with(LocalDensity.current){squareSize.toPx()}
    val swipeState = rememberSwipeableState(0)
    val anchors = mapOf(0f to 0,sizePx to 1)
    Box(
        modifier = Modifier
            .size(width, 40.dp)
            .background(color = Color.LightGray)
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal,
            )
    ) {
        Box(
            modifier = Modifier
                .size(squareSize)
                //注意这里要放在background前面，否则出现无法滑动问题
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
                .background(color = Color.Green)
        )
    }
}

/**
 * 手势 - 多点触控
 * 平移、缩放、旋转
 */
@Composable
private fun GestureOfMultiTouch(){
    var scale by remember{ mutableStateOf(1f)}
    var translate by remember{ mutableStateOf(Offset.Zero)}
    var rotation by remember{ mutableStateOf(0f)}

    val state = rememberTransformableState{zoomChange: Float, panChange: Offset, rotationChange: Float->
        scale *= zoomChange
        translate += panChange
        rotation += rotationChange
    }
    Box(
        modifier = Modifier
            .size(120.dp,80.dp)
            .graphicsLayer(
                scaleX = scale,   //x轴的缩放值
                scaleY = scale,   //y轴的缩放值
                translationX = translate.x,//x轴的平移值
                translationY = translate.y,//y轴的平移值
                rotationZ = rotation        //旋转值
            )
            .transformable(state)
            .background(Color.Blue)
    )
}