package com.zhh.app.navigation.demo

import kotlinx.coroutines.*
import org.junit.Test

/**
 * Created by zhanghai on 2020/5/22.
 * function：
 */
class CoroutineTest {

}

suspend fun fetchDocs() {                             // Dispatchers.Main
    val result = get("https://developer.android.com") // Dispatchers.IO for `get`
    println("result---->>> "+result)                        // Dispatchers.Main
}

suspend fun get(url: String) = withContext(Dispatchers.IO) {
    Thread.sleep(5000)
    url
}

fun main(args:Array<String>){
   var job =  GlobalScope.launch { fetchDocs() }

}