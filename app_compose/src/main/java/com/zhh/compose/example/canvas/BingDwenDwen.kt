package com.zhh.compose.example.canvas

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.zhh.compose.example.R
import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.math.tan

/**
 *
 * 通过手绘冰墩墩，练习Jetpack Compose Canvas绘图
 *
 * 使用到绘制图形方法：
 * 1.绘制圆形：drawCircle()
 * 2.绘制椭圆：drawOval()
 * 3.绘制弧形：drawArc()
 * 4.绘制Path：drawPath()
 * 5.绘制图形：drawImage()
 * 6.绘制文字：nativeCanvas.drawText()
 * 7.旋转：rotate()
 * 8.平移：translate()
 *
 */
@Composable
fun BingDwenDwen(modifier: Modifier=Modifier){
    val resource = LocalContext.current.resources
    val imageBitmap = ImageBitmap.imageResource(res = resource,id = R.mipmap.winter_olympics_logo)
    Canvas(modifier = modifier){

        //转换成原生canvas绘制，替换DrawScope
        drawIntoCanvas { canvas->

            val paint = Paint().apply {
                color = Color.Black
                strokeWidth = 3f
                isAntiAlias = true
                style = PaintingStyle.Stroke
            }

            canvas.save()
            //将坐标原点转换到屏幕中心
            canvas.translate(center.x,center.y)

            //原点上绘制一个位置参考圆形
            canvas.drawCircle(Offset(0f,0f),5f,paint)

            val oval1Size = Size(500f,600f)

            //步骤一：画身体
            firstStep(canvas, paint,oval1Size)
            //步骤二：绘制两个耳朵
            drawEars(canvas,paint)
            //步骤三：画手
            drawHandles(canvas,paint,oval1Size)
            //步骤四：画脚
            drawLegs(canvas,paint,oval1Size)
            //步骤五：画面部
            drawFiveOvals(canvas,paint)
            //步骤六：画眼睛
            drawEyes(canvas, paint)
            //步骤七：鼻子和嘴巴
            drawNoseAndMouth(canvas, paint)
            //步骤八：logo、文字、5环
            drawLogo(canvas, paint,imageBitmap)
            //步骤九：绘制发光效果
            drawLightEffect(canvas,paint,oval1Size)
            //坐标轴参考线
//            canvas.drawLine(Offset(0f,-oval1Size.height/2-200), Offset(0f,oval1Size.height/2+200),paint)
//            canvas.drawLine(Offset(-oval1Size.width/2-2000f,0f), Offset(oval1Size.width/2+200,0f),paint)
            canvas.restore()
        }

    }
}

//步骤一：先绘制一个宽x高=500x600的椭圆
private fun firstStep(canvas:Canvas,paint: Paint,ovalSize: Size){
    paint.strokeWidth = 10f
    //步骤一：绘制一个宽x高=500x600的椭圆
    canvas.drawOval(Rect(-ovalSize.width/2,-ovalSize.height/2,ovalSize.width/2,ovalSize.height/2),paint)
    paint.strokeWidth = 2f
}

//步骤二：绘制两个耳朵
private fun drawEars(canvas:Canvas,paint: Paint){
    //设置画笔填充
    paint.style = PaintingStyle.Fill
    //绘制左耳
    canvas.drawArc(
        rect = Rect(Offset(-180f,-310f),Size(90f,110f)),
        startAngle = -215f,
        sweepAngle = 185f,
        useCenter = false,
        paint = paint
    )

    //绘制右耳
    canvas.drawArc(
        rect = Rect(Offset(95f,-310f),Size(90f,110f)),
        startAngle = -160f,
        sweepAngle = 205f,
        useCenter = false,
        paint = paint
    )

    //回复画笔填充模式
    paint.style = PaintingStyle.Stroke

}
//步骤三：绘制左右手、红色爱心
private fun drawHandles(canvas:Canvas,paint: Paint,ovalSize: Size){

    paint.style = PaintingStyle.Fill
    //左
    val leftPath = Path().apply {
        val coordinate1 = getOvalSideCoordinate(ovalSize,170)
        //计算椭圆上坐标点
        val sx = coordinate1.x
        val sy = coordinate1.y

        val coordinate2 = getOvalSideCoordinate(ovalSize,155)
        val ex = coordinate2.x
        val ey = coordinate2.y

        moveTo(-sx,-sy)
        cubicTo(-sx,-sy,-sx-40,-sy+30,-sx-80,-sy+65)
        cubicTo(-sx-82,-sy+66,-sx-80-40,-sy+65+70,-sx-50,-sy+60+85)
        cubicTo(-sx-50,-sy+60+85,-ex-20,-ey+60+25,-ex,-ey)
        close()
    }
    canvas.drawPath(path = leftPath,paint)

    val coordinate2 = getOvalSideCoordinate(ovalSize,15)
    val ex = coordinate2.x
    val ey = coordinate2.y
    val rightPath = Path().apply {

        //右
        val coordinate1 = getOvalSideCoordinate(ovalSize,30)
        //计算椭圆上坐标点
        val sx = coordinate1.x
        val sy = coordinate1.y

        moveTo(sx,sy)
        cubicTo(sx,sy,sx+80,sy-50,sx+110,sy-110)
        cubicTo(sx+110,sy-110,sx+130,sy-150,sx+80,sy-180)
        cubicTo(sx+80,sy-180,sx+35,sy-210,ex,ey)
        close()
    }
    canvas.drawPath(path = rightPath,paint)


    val hearPath = Path().apply {
        moveTo(ex+50,ey-70)
        //左半部分
        cubicTo(ex+50,ey-70,ex+50,ey-90,ex+35,ey-80)
        cubicTo(ex+35,ey-80,ex+20,ey-60,ex+40,ey-40)

        //右半部分
        cubicTo(ex+40,ey-40,ex+80,ey-60,ex+60,ey-78)
        cubicTo(ex+60,ey-78,ex+50,ey-80,ex+45,ey-70)
        close()
    }
    paint.color = Color.Red
    canvas.drawPath(path = hearPath,paint)
    paint.color = Color.Black
    paint.style = PaintingStyle.Stroke
}
//步骤四：画脚
private fun drawLegs(canvas: Canvas, paint: Paint, oval1Size: Size) {
    val angle = 90
    val height = 100 //腿高
    val offsetAngle1 = 30
    val offsetAngle2 = 7
    paint.style = PaintingStyle.Fill
    //左脚
    val leftPath = Path().apply {
        //左边起始点
        val point1 = getOvalSideCoordinate(oval1Size,angle+offsetAngle1)
        //右边结束点
        val point2 = getOvalSideCoordinate(oval1Size,angle+offsetAngle2)
        //从左边起始点开始
        moveTo(-point1.x,-point1.y)
        lineTo(-point1.x,-point1.y + height-10)
        cubicTo(-point1.x,-point1.y + height-10,-point1.x,-point1.y+height+10,-point1.x+15,-point1.y+height+10)
        lineTo(-point2.x,-point1.y+height+10)
        cubicTo(-point2.x,-point1.y+height+10,-point2.x+10,-point1.y+height+10,-point2.x,-point1.y + height-5)
        lineTo(-point2.x,-point2.y)
        close()
    }
    canvas.drawPath(leftPath,paint)

    //右脚
    val rightPath = Path().apply {
        //右边起始点
        val point1 = getOvalSideCoordinate(oval1Size,angle-offsetAngle1)
        //左边结束点
        val point2 = getOvalSideCoordinate(oval1Size,angle-offsetAngle2)
        //从右边起始点开始绘制
        moveTo(point1.x,point1.y)
        lineTo(point1.x,point1.y + height-10)
        cubicTo(point1.x,point1.y + height-10,point1.x,point1.y+height+10,point1.x-15,point1.y+height+10)
        lineTo(point2.x,point1.y+height+10)
        cubicTo(point2.x,point1.y+height+10,point2.x-10,point1.y+height+10,point2.x,point1.y + height-5)
        lineTo(point2.x,point2.y)
        close()
    }
    canvas.drawPath(rightPath,paint)
    //恢复填充
    paint.style = PaintingStyle.Stroke
}
//步骤五：面部，绘制5色仅贴着的椭圆，颜色依次：蓝->红->紫->黄->绿
private fun drawFiveOvals(canvas: Canvas, paint: Paint){
    paint.strokeWidth = 7f
    //最里面椭圆宽度和高度
    var width = 360f
    var height = 300f
    //从最里面圆环向外依次width和height需要增加的值
    val offValue = paint.strokeWidth * 2
    val colors = arrayOf(Color(0xff87CEEB),Color(0xff8B0000),Color(0xff6A5ACD),Color(0xffFFD700),Color(0xff32CD32))
    //不包含5
    for (i in 0 until 5){
        paint.color = colors[i]
        canvas.drawOval(Rect(Offset(-width/2,-(height-70 - paint.strokeWidth * i)), Size(width,height)),paint)
        width += offValue
        height += offValue
    }
    //恢复
    paint.strokeWidth = 5f
    paint.color = Color.Black
}
//步骤六：眼睛
private fun drawEyes(canvas: Canvas, paint: Paint){
    val width = 110f
    val height = 150f
    canvas.apply {
        save()
        rotate(40f)
        translate(-80f,100f)
        paint.style = PaintingStyle.Fill
        drawOval(Rect(Offset(-120f,-200f), Size(width,height)),paint)
        paint.style = PaintingStyle.Stroke
        paint.color = Color.White
        drawCircle(Offset(-55f,-140f),35f,paint)
        paint.style = PaintingStyle.Fill
        drawCircle(Offset(-50f,-155f),10f,paint)
        restore()

        save()
        rotate(-40f)
        translate(-30f,100f)
        paint.color = Color.Black
        paint.style = PaintingStyle.Fill
        drawOval(Rect(Offset(120f,-200f), Size(width,height)),paint)
        paint.style = PaintingStyle.Stroke
        paint.color = Color.White
        drawCircle(Offset(165f,-140f),35f,paint)
        paint.style = PaintingStyle.Fill
        drawCircle(Offset(180f,-135f),10f,paint)
        restore()
    }
    paint.style = PaintingStyle.Stroke
    paint.color = Color.Black
}
//步骤七：鼻子和嘴巴
private fun drawNoseAndMouth(canvas: Canvas, paint: Paint){
    canvas.apply {
        val noseWidth = 30f
        val noseHeight = 30f
        val path = Path().apply {
            moveTo(-noseWidth/2,-100f)
            cubicTo(-noseWidth/2,-100f,-noseWidth,-(100-noseHeight/2),0f,-(100 - noseHeight))
            cubicTo(0f,-(100 - noseHeight),noseWidth,-(100-noseHeight/2),noseWidth/2,-100f)
            close()
        }
        paint.style = PaintingStyle.Fill
        drawPath(path,paint)

        paint.style = PaintingStyle.Fill
        drawArc(-(noseWidth+100)/2,-70f,(noseWidth+100)/2,40f,-55f,290f,false,paint)
        paint.color = Color.White
        drawArc(-(noseWidth+40)/2,-80f,(noseWidth+40)/2,-50f,0f,180f,false,paint)
        paint.color = Color(0xffA52A2A)
        drawOval(-(noseWidth+60)/2,-20f,(noseWidth+60)/2,36f,paint)
        paint.color = Color.Black
    }
}

//步骤八：logo、文字、5环
private fun drawLogo(canvas: Canvas, paint: Paint,imageBitmap: ImageBitmap){
    canvas.apply {
        //绘制logo
        drawImage(imageBitmap, Offset(-imageBitmap.width/2f,imageBitmap.height/2f + 90),paint)

        //绘制文字
        val text="BEIJING 2022"
        //创建原生Paint对象
        val nativePaint = android.graphics.Paint().apply {
            color = android.graphics.Color.BLACK
            strokeWidth = 2f
            textSize = 22f
            textSkewX = -0.5f
            typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD)
        }
        //测量文字宽度
        val width = nativePaint.measureText(text)
        //注意：这里绘制文字要使用nativeCanvas,即使用原生的canvas绘制，Compose提供的Canvas不支持绘制文字
        nativeCanvas.drawText(text,-width/2,220f,nativePaint)

        //绘制五个圆环叠在一起，上面3个，下面2个，
        //下面两个相对上面三个的y轴偏移量：圆环的半径
        //上面三个圆环间距为5
        val colors = arrayOf(Color(0xff87CEEB),Color(0xff000000),Color(0xff8B0000),Color(0xffFFD700),Color(0xff32CD32))
        val radius = 12f   //圆环半径
        val offsetY = 240f //上面圆环距离绘图中心点y轴上的偏移量
        val space = 5f     //圆环间距
        paint.style = PaintingStyle.Stroke
        paint.strokeWidth = 3f  //圆环线条粗细
        for (i in 0 until 5){
            paint.color = colors[i]
            if (i < 3){//上层圆环
                drawCircle(Offset((radius * 2 + space) * (i-1),offsetY),radius, paint)
            }else{//下层圆环
                val sel = if(i <= 3) -1 else 1
                drawCircle(Offset(sel * (radius + space/2),offsetY + radius),radius, paint)
            }
        }
        //画出交叉感觉
        paint.color = colors[0]
        //画出蓝环扣在黄环中
        drawArc(Rect(Offset(-radius * 2 - space,offsetY),radius = radius),-10f,30f,false,paint)
        paint.color = colors[1]
        //黑环 一部分扣在黄环上 一部分扣在绿环上
        drawArc(Rect(Offset(0f,offsetY),radius = radius),90f,30f,false,paint)
        drawArc(Rect(Offset(0f,offsetY),radius = radius),-10f,30f,false,paint)
        paint.color = colors[2]
        //绘制红环扣在绿环中
        drawArc(Rect(Offset(radius * 2 + space,offsetY),radius = radius),90f,30f,false,paint)

        //恢复画笔颜色
        paint.color = Color.Black
    }
}

//步骤九：绘制发光效果
private fun drawLightEffect(canvas: Canvas, paint: Paint,oval1Size: Size){
    val halfHeight = oval1Size.height / 2
    val halfWidth = oval1Size.width / 2
    val lightPath = Path().apply {
        moveTo(90f,-halfHeight)

        cubicTo(90f,-halfHeight,0f,-halfHeight - 50,-90f,-halfHeight)

        cubicTo(-90f,-halfHeight,-120f, -halfHeight - 50,-175f,-halfHeight-7)
        cubicTo(-175f,-halfHeight-7,-210f,-halfHeight+20,-190f,-halfHeight+70)

        cubicTo(-190f,-halfHeight+70,-280f,-halfHeight/2,-halfWidth-24,35f)

        cubicTo(-halfWidth-24,35f,-halfWidth-130,90f,-halfWidth-100,170f)
        cubicTo(-halfWidth-100,170f,-halfWidth-60,230f,-halfWidth+10,170f)

        cubicTo(-halfWidth+10,170f,-halfWidth+50,250f,-halfWidth+90,280f)

        cubicTo(-halfWidth+90,280f,-halfWidth+90,halfHeight,-halfWidth+90,halfHeight+50)
        cubicTo(-halfWidth+90,halfHeight+50,-halfWidth+90,halfHeight+70,-halfWidth+100,halfHeight+70)
        lineTo(-40f,halfHeight+70)
        cubicTo(-40f,halfHeight+70,-10f,halfHeight+70,-30f,halfHeight+30)

        cubicTo(-30f,halfHeight+30,-30f,halfHeight+20,20f,halfHeight+20)

        cubicTo(20f,halfHeight+20,30f,halfHeight+20f,20f,halfHeight+60)

        cubicTo(20f,halfHeight+60,20f,halfHeight+75f,130f,halfHeight+70)
        cubicTo(130f,halfHeight+70,166f,halfHeight+70,155f,halfHeight-30)

        cubicTo(155f,halfHeight-30,155f,halfHeight-40,165f,halfHeight-50)
        cubicTo(160f,halfHeight-50,200f,halfHeight-50,halfWidth,140f)

        cubicTo(halfWidth,140f,halfWidth+170,30f,halfWidth+80,-50f)

        cubicTo(halfWidth+80,-50f,halfWidth+30,-90f,halfWidth+10,-40f)

        cubicTo(halfWidth+10,-40f,halfWidth+20,-140f,halfWidth-60,-halfHeight+70)

        cubicTo(halfWidth-60,-halfHeight+70,halfWidth-30,-halfHeight+30,halfWidth-80,-halfHeight-10)

        cubicTo(halfWidth-80,-halfHeight-10,halfWidth-120,-halfHeight-40,90f,-halfHeight)
    }
    canvas.drawPath(lightPath,paint)
}

/**
 * 获取椭圆上任意角度的点的坐标
 */
private fun getOvalSideCoordinate(ovalSize:Size,angle:Int):Offset{
    val halfWidth = ovalSize.width / 2
    val halfHeight = ovalSize.height / 2
    val arcSweep = PI * angle / 180
    val sx = ((halfWidth * halfHeight) / sqrt(halfHeight * halfHeight + halfWidth * halfWidth * tan(arcSweep)*tan(arcSweep))).toFloat()
    val sy = (sx * tan(arcSweep)).toFloat()
    return Offset(sx,sy)
}

@Preview
@Composable
fun previewBingDwenDwen(){
    BingDwenDwen(modifier = Modifier.fillMaxSize())
}