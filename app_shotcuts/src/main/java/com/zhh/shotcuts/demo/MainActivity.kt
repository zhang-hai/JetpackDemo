package com.zhh.shotcuts.demo

import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Color
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.zhh.shotcuts.demo.ui.login.LoginActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jumpToLoginUI(v: View){
        val i = Intent(this,LoginActivity::class.java)
        startActivity(i)
    }

    fun createDynamicShortCuts(v:View){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1){
            var dynamicShortCut = getSystemService(ShortcutManager::class.java)
            var list = ArrayList<ShortcutInfo>()
            list.add(createShortcutInfo1())
            list.add(createShortcutInfo2())
            dynamicShortCut?.addDynamicShortcuts(list)

            Toast.makeText(this,"动态添加快捷方式成功",Toast.LENGTH_SHORT).show()
        }
    }



    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun createShortcutInfo1():ShortcutInfo{
        return ShortcutInfo.Builder(this,"id_dynamic_search")
                .setLongLabel(getString(R.string.str_search_label_dynamic))
                .setShortLabel(getString(R.string.str_search_label_dynamic))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_me_collection))
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/zhang-hai")))
                .build()
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    private fun createShortcutInfo2(): ShortcutInfo {
        val intent = Intent(this, LoginActivity::class.java)
        intent.action = Intent.ACTION_VIEW
        intent.putExtra("key", "fromDynamicShortcut")
        val colorSpan = ForegroundColorSpan(Color.BLUE)
        val label = getString(R.string.str_login_label_dynamic)
        val colouredLabel = SpannableStringBuilder(label)
        colouredLabel.setSpan(colorSpan, 0, label.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        return ShortcutInfo.Builder(this, "id_dynamic_login")
                .setShortLabel(colouredLabel)
                .setLongLabel(colouredLabel)
                .setIcon(Icon.createWithResource(this, R.drawable.ic_me_emoj))
                .setIntent(intent)
                .build()
    }
}
