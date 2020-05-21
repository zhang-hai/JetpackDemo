package com.zhh.app.navigation.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType

/**
 * Created by zhanghai on 2020/5/21.
 * function：
 */
abstract class BaseAdapter<E,V : ViewBinding>(context: Context, items:List<E>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val HEADER_VIEW = 0x00000111
    val LOADING_VIEW = 0x00000222
    val FOOTER_VIEW = 0x00000333
    val EMPTY_VIEW = 0x00000555

    var items:List<E>? = null
    var context:Context? = null
    var viewBinding : V? = null
    var inflater:LayoutInflater

    var mHeaderLayout: LinearLayout? = null
    var mFooterLayout: LinearLayout? = null

    init {
        this.items = items
        this.context = context
        this.inflater = LayoutInflater.from(context)
    }

    /**
     * 绑定布局文件
     */
    abstract fun bindView(inflater: LayoutInflater,parent: ViewGroup)

    /**
     * 绑定数据
     */
    abstract fun convert(position:Int)

    /**
     * view的点击事件
     */
    abstract fun bindViewClickListener(holder: BaseViewHolder)


    fun getClickPosition(holder:BaseViewHolder):Int{
        var p = holder.absoluteAdapterPosition - getHeaderLayoutCount();
        return if(p >= 0){
            p
        }else{
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder:BaseViewHolder
            when(viewType){
            HEADER_VIEW -> holder = createBaseViewHolder(mHeaderLayout!!)
            FOOTER_VIEW-> holder = createBaseViewHolder(mFooterLayout!!)
            else->{
                bindView(inflater,parent)
                holder = BaseViewHolder(viewBinding!!.root)
                bindViewClickListener(holder)
            }
        }

        return holder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        convert(position - getHeaderLayoutCount())
    }

    protected open fun createBaseViewHolder(view: View): BaseViewHolder {
        var temp: Class<*>? = javaClass
        var z: Class<*>? = null
        while (z == null && null != temp) {
            z = getInstancedGenericKClass(temp)
            temp = temp.superclass
        }
        val k: BaseViewHolder
        // 泛型擦除会导致z为null
        k = z?.let { createGenericKInstance(it, view) } ?: BaseViewHolder(view) as BaseViewHolder
        return if (k != null) k else BaseViewHolder(view) as BaseViewHolder
    }

    /**
     * try to create Generic K instance
     *
     * @param z
     * @param view
     * @return
     */
    private fun createGenericKInstance(z: Class<*>, view: View): BaseViewHolder? {
        try {
            val constructor: Constructor<*>
            // inner and unstatic class
            return if (z.isMemberClass && !Modifier.isStatic(z.modifiers)) {
                constructor = z.getDeclaredConstructor(javaClass, View::class.java)
                constructor.isAccessible = true
                constructor.newInstance(this, view) as BaseViewHolder
            } else {
                constructor = z.getDeclaredConstructor(View::class.java)
                constructor.isAccessible = true
                constructor.newInstance(view) as BaseViewHolder
            }
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * get generic parameter K
     *
     * @param z
     * @return
     */
    private fun getInstancedGenericKClass(z: Class<*>): Class<*>? {
        val type = z.genericSuperclass
        if (type is ParameterizedType) {
            val types = type.actualTypeArguments
            for (temp in types) {
                if (temp is Class<*>) {
                    val tempClass = temp
                    if (BaseViewHolder::class.java.isAssignableFrom(tempClass)) {
                        return tempClass
                    }
                } else if (temp is ParameterizedType) {
                    val rawType = temp.rawType
                    if (rawType is Class<*> && BaseViewHolder::class.java.isAssignableFrom(rawType)) {
                        return rawType
                    }
                }
            }
        }
        return null
    }


    override fun getItemCount(): Int {
        var count: Int
        if (items == null) {
            count = 0
        }

        count = getHeaderLayoutCount() + this.items!!.size + getFooterLayoutCount()

        return count
    }

    override fun getItemViewType(position: Int): Int {
        val numHeaders = getHeaderLayoutCount()
        return if (position < numHeaders) {
            HEADER_VIEW
        } else {
            var adjPosition = position - numHeaders
            val adapterCount: Int = items!!.size
            if (adjPosition < adapterCount) {
                getDefItemViewType(adjPosition)
            } else {
                adjPosition -= adapterCount
                val numFooters = getFooterLayoutCount()
                if (adjPosition < numFooters) {
                    FOOTER_VIEW
                } else {
                    LOADING_VIEW
                }
            }
        }
    }

    protected open fun getDefItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    /**
     * if addHeaderView will be return 1, if not will be return 0
     */
    open fun getHeaderLayoutCount(): Int {
        return if (mHeaderLayout == null || mHeaderLayout!!.childCount == 0) {
            0
        } else 1
    }

    /**
     * if addFooterView will be return 1, if not will be return 0
     */
    open fun getFooterLayoutCount(): Int {
        return if (mFooterLayout == null || mFooterLayout!!.childCount == 0) {
            0
        } else 1
    }

    fun addHeaderView(header:View,index:Int) : Int{
        return addHeaderView(header, index,LinearLayout.VERTICAL)
    }

    fun addHeaderView(header:View) : Int{
        return addHeaderView(header, -1)
    }

    /**
     * 添加头view
     */
    fun addHeaderView(header:View,index:Int,orientation:Int) : Int{
        var hI = index
        if (mHeaderLayout == null) {
            mHeaderLayout = LinearLayout(header.context)
            if (orientation == LinearLayout.VERTICAL) {
                mHeaderLayout!!.orientation = LinearLayout.VERTICAL
                mHeaderLayout!!.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            } else {
                mHeaderLayout!!.orientation = LinearLayout.HORIZONTAL
                mHeaderLayout!!.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
        }
        val childCount = mHeaderLayout!!.childCount
        if (hI < 0 || hI > childCount) {
            hI = childCount
        }
        mHeaderLayout!!.addView(header, hI)
        if (mHeaderLayout!!.childCount == 1) {
            val position: Int = getHeaderViewPosition()
            if (position != -1) {
                notifyItemInserted(position)
            }
        }
        return hI
    }


    /**
     * //Return to header view notify position
     */
    private fun getHeaderViewPosition(): Int {
        return 0
    }

    /**
     * Return to footer view notify position
     */
    private fun getFooterViewPosition():Int{
        return getHeaderLayoutCount() + items!!.size
    }















    class BaseViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}