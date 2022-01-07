package com.zhh.compose.example.exam02

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StaggeredGridLayout(modifier: Modifier = Modifier){

}


@Composable
fun ItemView(modifier: Modifier = Modifier,text:String){
    Card(modifier = Modifier.border(width = 1.dp,color = MaterialTheme.colors.onSecondary,shape = RoundedCornerShape(5.dp))
    ) {
        Text(modifier = Modifier.padding(horizontal = 10.dp,vertical = 5.dp),text = text)
    }
}