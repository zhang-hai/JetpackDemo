package com.zhh.compose.example.animate

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.zhh.compose.example.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Jetpack Compose动画
 *
 * 1.内容动画
 *
 * 2.多值动画
 *
 * 3.重复动画
 *
 *
 */
@Preview
@Composable
private fun PreviewContent(){
    StudyAnimation()
}

@Composable
fun StudyAnimation(){

    Column {
        ContentAnimation()

        ValueAnimations()

        CustomAnimations()

        GestureAnimation()
    }

}

/**
 * 1.内容动画
 * AnimatedVisibility、
 * AnimatedContent、
 * animateContentSize、
 * Crossfade
 *
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ContentAnimation(){
    var state1 by remember {
        mutableStateOf(true)
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var member by remember {
        mutableStateOf(0)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var crossFadeState by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        Text(text = "内容动画",modifier = Modifier.fillMaxWidth())
        Divider(modifier = Modifier.fillMaxWidth(),color = Color.Blue)
        Text(text = "1.AnimatedVisibility",Modifier.clickable { state1 = !state1 },color = Color.Red)

        //内容整体做动画
        AnimatedVisibility(
            visible = state1,
            enter = slideInVertically()+ fadeIn(),
            exit = slideOutVertically()+ fadeOut()
        ) {
            Text(text = "内容出现与消失动画")
        }

        //单独对某个组件设置动画
        AnimatedVisibility(visible = state1,enter = EnterTransition.None,exit = ExitTransition.None) {
            Row {
                Icon(imageVector = Icons.Default.Call, contentDescription = "logo"
                    ,modifier = Modifier.animateEnterExit(enter = fadeIn(),exit = fadeOut()))
                Text(text = "拨打电话")
            }
        }

        Divider(modifier = Modifier.fillMaxWidth(),color = Color.Blue)
        Text(text = "2.AnimatedContent",Modifier.clickable { member++ },color = Color.Red)
        AnimatedContent(targetState = member,
            transitionSpec ={
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically({ height -> height }) + fadeIn() with
                            slideOutVertically({ height -> -height }) + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically({ height -> -height })+ fadeIn() with
                            slideOutVertically({ height -> height }) + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) {targetCount->
            Text(text = "$targetCount")
        }
        //SizeTransform 定义了大小应如何在初始内容与目标内容之间添加动画效果
        Box(contentAlignment= Alignment.Center,
            modifier = Modifier
                .defaultMinSize(50.dp, 30.dp)
                .background(color = Color.Green)
                .clickable { expanded = !expanded }
        ) {
            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        // Expand horizontally first.
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        // Shrink vertically first.
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }
            ) {targetExpanded->
                if (targetExpanded){
                    Text(
                        text = "内容展开了哈哈哈哈哈哈哈哈哈哈哈\n内容展开了哈哈哈哈哈哈哈哈哈哈哈\n内容展开了哈哈哈哈哈哈哈哈哈哈哈",
                        modifier = Modifier.fillMaxWidth()
                    )
                }else{
                    Icon(imageVector = Icons.Default.Call, contentDescription = "call")
                }
            }
        }

        Divider(modifier = Modifier.fillMaxWidth(),color = Color.Blue)
        Text(text = "3.animateContentSize",color = Color.Red)
        Text(text = if (!isExpanded) "点我展开" else "点我收起\n收起",modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { isExpanded = !isExpanded })

        Divider(modifier = Modifier.fillMaxWidth(),color = Color.Blue)
        Text(text = "4.Crossfade",
            color = Color.Red,
            modifier = Modifier
                .size(200.dp, 40.dp)
                .clickable { crossFadeState = !crossFadeState }
        )
        Row {
            //UI切换 带淡入淡出动画
            Crossfade(targetState = crossFadeState) {
                Box(modifier = Modifier.background(color = if (it) Color.Green else Color.Gray)) {
                    if (it) Text(text = "Page A",)
                    else Text(text = "Page B")
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            //UI切换 不带动画
            Box(modifier = Modifier.background(color = if (crossFadeState) Color.Green else Color.Gray)) {
                if (crossFadeState) Text(text = "Page A",)
                else Text(text = "Page B")
            }
        }
    }
}


/**
 * 2.值动画
 */
@Composable
private fun ValueAnimations(){

    Text(text = "值动画",modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
    Row(modifier = Modifier.fillMaxWidth()) {
        MultiValueAnimation()
        SingleValueAnimation()
        AnimatableAnimation()
    }
    InfiniteTransitionAnimation()

}

/**
 * 3.自定义动画
 */
@Composable
private fun CustomAnimations(){
    Row(modifier = Modifier.fillMaxWidth()) {
        springAnimation()

        Spacer(modifier = Modifier.width(5.dp))
        tweenAnimation()

        Spacer(modifier = Modifier.width(5.dp))
        keyframesAnimation()
    }
    Spacer(modifier = Modifier.height(5.dp))
    Row() {
        repeatableAnimation()
        Spacer(modifier = Modifier.width(5.dp))
        InfiniteRepeatableAnimation()
        Spacer(modifier = Modifier.width(5.dp))
        SnapAnimation()
    }
}

/**
 * 4.手势动画
 */
@Composable
private fun GestureAnimation(){
    Gesture()
}


enum class BoxState {
    Collapsed,
    Expanded
}

/**
 * 多值动画
 *
 */
@Composable
private fun MultiValueAnimation(){
    val targetState = remember {
        mutableStateOf(BoxState.Collapsed)
    }
    val transition = updateTransition(
        targetState = targetState,
        label = "hahah"
    )

    val rect by transition.animateRect { state ->
        when (state.value) {
            BoxState.Collapsed -> Rect(0f, 0f, 100f, 100f)
            BoxState.Expanded -> Rect(100f, 100f, 300f, 300f)
        }
    }
    val borderWidth by transition.animateDp { state ->
        when (state.value) {
            BoxState.Collapsed -> 1.dp
            BoxState.Expanded -> 0.dp
        }
    }
    val color by transition.animateColor {state ->
        when(state.value){
            BoxState.Expanded->Color.LightGray
            BoxState.Collapsed->Color.Gray
        }
    }
    Surface(shape = RectangleShape,
        border = BorderStroke(width = borderWidth,color = Color.Blue),
        modifier = Modifier
            .size(rect.width.dp, rect.height.dp)
            .clickable {
                if (targetState.value == BoxState.Expanded) targetState.value =
                    BoxState.Collapsed else targetState.value = BoxState.Expanded
            },
        color = color
    ) {
        Text(text = "多值动画")
    }
}

/**
 * 单值动画
 * animateValueAsState
 * animateFloatAsState
 * animateColorAsState
 * animateSizeAsState
 * animateDpAsState
 * animateOffsetAsState
 *
 */
@Composable
private fun SingleValueAnimation(){
    var enabled by remember {
        mutableStateOf(false)
    }
    //使用animateFloatAsState变化透明度
    val alpha: Float by animateFloatAsState(if (enabled) 1f else 0.5f)
    val offset by animateOffsetAsState(targetValue = if (enabled) Offset.Zero else Offset(10f,10f))
    Image(
        painter = painterResource(id = R.mipmap.ic_girl),
        contentDescription = "avatar",
        modifier = Modifier
            .offset(offset.x.dp, offset.y.dp)
            .alpha(alpha)
            .clickable { enabled = !enabled }
    )
}

/**
 * 通过Animatable 是一个值容器实现动画
 */
@Composable
private fun AnimatableAnimation(){
    var enabled by remember {
        mutableStateOf(false)
    }
    val color = remember { Animatable(Color.Gray) }
    LaunchedEffect(enabled) {
        color.animateTo(if (enabled) Color.Green else Color.Red)
    }
    Box(
        Modifier
            .size(60.dp)
            .background(color.value)
            .clickable { enabled = !enabled }
    )
}

/**
 * InfiniteTransition实现动画
 */
@Composable
private fun InfiniteTransitionAnimation(){
    val infiniteTransition = rememberInfiniteTransition()
    val state by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Cyan,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000,easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ))
    Box(
        Modifier
            .size(60.dp)
            .background(state)
    )
}

/**
 * 弹性动画
 */
@Composable
private fun springAnimation(){
    var enable by remember{ mutableStateOf(true)}
    val value: Int by animateIntAsState(
        targetValue = if (enable) 200 else 50,
        // Configure the animation duration and easing.
        animationSpec = spring(
            //定义弹簧的弹性
            dampingRatio = Spring.DampingRatioHighBouncy,
            //定义弹簧应向结束值移动的速度
            stiffness = Spring.StiffnessHigh
        )
    )
    Box(
        Modifier
            .offset(50.dp)
            .width(value.dp)
            .height(50.dp)
            .background(Color.Blue)
            .clickable { enable = !enable }
    ){
        Text(text = "spring")
    }
}

/**
 * 补间动画
 */
@Composable
private fun tweenAnimation(){
    var enable by remember{ mutableStateOf(false)}
    val value by animateIntAsState(
        targetValue = if (enable) 150 else 50,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )
    Box(
        Modifier
            .width(value.dp)
            .height(50.dp)
            .background(Color.Gray)
            .clickable { enable = !enable }
    ) {
        Text(text = "tween")
    }
}

/**
 * 关键帧动画
 */
@Composable
private fun keyframesAnimation(){
    var enable by remember{ mutableStateOf(false)}

    val value by animateIntAsState(
        targetValue = if (enable) 200 else 50,
        animationSpec = keyframes {
            durationMillis = 2000   //动画执行时长
            delayMillis = 500       //动画延迟多久后执行
            50 at 0 with LinearOutSlowInEasing   //0 - 200ms执行的帧
            100 at 200 with FastOutLinearInEasing // 200 - 1200ms执行的帧
            150 at 1200 with LinearEasing
        }
    )
    Box(
        Modifier
            .height(50.dp)
            .width(value.dp)
            .background(Color.Yellow)
            .clickable { enable = !enable }
    ) {
       Text(text = "keyframes")
    }
}

/**
 * 重复动画
 */
@Composable
private fun repeatableAnimation(){
    var enable by remember{ mutableStateOf(false)}

    val value by animateIntAsState(
        targetValue = if (enable) 200 else 50,
        animationSpec = repeatable(
            iterations = 2,   //重复执行次数
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse  //重复执行模式，从最后开始
        )
    )
    Box(
        Modifier
            .height(50.dp)
            .width(value.dp)
            .background(Color.Red)
            .clickable { enable = !enable }
    ) {
        Text(text = "repeatable")
    }
}

/**
 * 无限次重复动画
 */
@Composable
private fun InfiniteRepeatableAnimation(){
    var enable by remember{ mutableStateOf(false)}

    val value by animateIntAsState(
        targetValue = if (enable) 200 else 50,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse  //重复执行模式，从最后开始
        )
    )
    Box(
        Modifier
            .height(50.dp)
            .width(value.dp)
            .background(Color.Green)
            .clickable { enable = !enable }
    ) {
        Text(text = "infiniteRepeatable")
    }
}

/**
 * 立即将值切换到结束值的动画
 */
@Composable
private fun SnapAnimation(){
    var enable by remember{ mutableStateOf(false)}

    val value by animateIntAsState(
        targetValue = if (enable) 200 else 50,
        animationSpec = snap(
            delayMillis = 1000   //延迟1000ms执行
        )
    )
    Box(
        Modifier
            .height(50.dp)
            .width(value.dp)
            .background(Color.Black)
            .clickable { enable = !enable }
    ) {
        Text(text = "snap",color = Color.White)
    }
}

private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

/**
 * 通过手势点击，设置偏移量动画
 */
@Composable
private fun Gesture(){
    val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        // Detect a tap event and obtain its position.
                        val position = awaitPointerEventScope {
                            awaitFirstDown().position
                        }
                        launch {
                            // Animate to the tap position.
                            offset.animateTo(position)
                        }
                    }
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_girl),
            contentDescription = "avatar",
            modifier = Modifier.offset { offset.value.toIntOffset() }
        )
    }
}
