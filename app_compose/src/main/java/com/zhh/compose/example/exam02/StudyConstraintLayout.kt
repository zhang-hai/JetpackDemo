package com.zhh.compose.example.exam02

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import kotlinx.coroutines.launch

/**
 * 学习Compose布局中的ConstraintLayout
 *
 * 在 Android View 系统中，使用 ConstraintLayout 作为构建更高性能布局的一种方法，但这在 Compose 中并不是问题。
 *
 * 所以这里仅作为一种布局方式
 *
 * 使用步骤：
 *
 * 步骤一、使用 createRefs() 或 createRef() 创建与组件关联的引用
 * 步骤二、通过给constraintAs修饰符设置与之关联的引用
 * 步骤三、调用linkTo()设置约束
 */

/**
 * 方式一：直接在ConstraintLayout中内部设置约束信息
 */
@Composable
fun StudyConstraintLayout(){

    val userNameState = remember { mutableStateOf("") }
    val userPwdState = remember { mutableStateOf("") }

    val coroutine = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()

    // Constraint 布局
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        //步骤一、使用 createRefs() 或 createRef() 创建与组件关联的引用
        val (textTitle,tfUser,tfPassword,btnLogin,snack) = createRefs()

        Text(
            text = "登录界面",
            modifier = Modifier
                //步骤二、通过给constraintAs修饰符设置与之关联的引用
                .constrainAs(textTitle) {
                    //步骤三、调用linkTo()设置约束
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    //设置顶部约束和margin
                    top.linkTo(parent.top,20.dp)
                }
        )
        //用户名输入框
        OutlinedTextField(
            value = userNameState.value,
            onValueChange = {userNameState.value = it},
            singleLine = true,
            label = { Text("用户名")},
            shape= RoundedCornerShape(50),
            placeholder = { Text("请输入用户名")},
            leadingIcon = { Icon(painter = rememberVectorPainter(Icons.Default.AccountCircle),contentDescription = "用户名")},
            modifier = Modifier
                .constrainAs(tfUser) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textTitle.bottom,10.dp)
                }
        )

        //密码输入框
        OutlinedTextField(
            value = userPwdState.value,
            onValueChange = {userPwdState.value = it},
            singleLine = true,
            label = { Text("密码")},
            shape= RoundedCornerShape(50),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text("请输入密码")},
            leadingIcon = { Icon(painter = rememberVectorPainter(Icons.Default.Lock),contentDescription = "密码")},
            modifier = Modifier
                .constrainAs(tfPassword) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(tfUser.bottom)
                }
                .padding(top = 10.dp)
        )

        OutlinedButton(
            onClick = {
                coroutine.launch {
                    snackbarHostState.showSnackbar("用户名：${userNameState.value},密码：${userPwdState.value}")
                }
            },
            enabled = userNameState.value.isNotEmpty() && userPwdState.value.isNotEmpty(),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .constrainAs(btnLogin) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(tfPassword.bottom)
                }
                .padding(top = 20.dp, bottom = 20.dp)
                .size(width = 120.dp, height = 35.dp)
        ){
            Text(text = "登录")
        }

        // 用于显示一个提示信息
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .wrapContentHeight(Alignment.Bottom)
                .constrainAs(snack) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}


/**
 * 方式二、通过使用ConstraintSet设置约束
 */
@Composable
fun StudyConstraintSet(){
    val constraintSet = decoupledConstraints(0.dp)

    ConstraintLayout(constraintSet) {
        Button(
            onClick = { /* Do something */ },
            //通知设置layoutId实现控制约束
            modifier = Modifier.layoutId("button")
        ) {
            Text("Button")
        }

        Text("Text", Modifier.layoutId("text"))
    }
}

/**
 * 创建一个ConstraintSet实例
 */
private fun decoupledConstraints(margin:Dp):ConstraintSet{
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button){
            top.linkTo(parent.top)
        }

        constrain(text){
            top.linkTo(button.bottom,10.dp)
        }
    }
}


@Preview(name = "方式一")
@Composable
fun PreviewStudyConstraintLayout1(){
    StudyConstraintLayout()
}

@Preview(name = "方式二 ConstraintSet")
@Composable
fun PreviewStudyConstraintLayout2(){
    StudyConstraintSet()
}