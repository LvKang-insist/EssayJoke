package com.qs.essayjoke.activity

import android.annotation.SuppressLint
import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class HookStartActivityUtil {

    @SuppressLint("PrivateApi")
    fun hookStartActivity() {

        //1，获取 ActivityTaskManager 中的 IActivityTaskManagerSingleton 属性
        val tmClass: Class<*> = Class.forName("android.app.ActivityTaskManager")
        //获取属性
        val iActivityTaskManagerSingletonField =
            tmClass.getDeclaredField("IActivityTaskManagerSingleton")
        //设置权限
        iActivityTaskManagerSingletonField.isAccessible = true
        //获取属性值，因为是静态的，所以传入 null 即可
        val iActivityTaskManagerSingleton = iActivityTaskManagerSingletonField.get(null)


        //	2， 获取 IActivityTaskManagerSingleton 中的 mInstance  属性
        val singletonClass = Class.forName("android.util.Singleton")
        //获取属性
        val mInstanceFiled = singletonClass.getDeclaredField("mInstance")
        mInstanceFiled.isAccessible = true
        //获取属性值，获取属性值要传入该类对象，因 iActivityTaskManagerSingleton 就是他的实例子类
        //所以直接传入即可
        var iamInstance = mInstanceFiled.get(iActivityTaskManagerSingleton)


        val iamClass: Class<*> = Class.forName("android.app.IActivityTaskManager");

        Log.e("--$iamClass------->", "${iamInstance}")


        iamInstance = Proxy.newProxyInstance(
            HookStartActivityUtil::class.java.classLoader,
            arrayOf(iamClass), StartActivityInvocationHandler(iamInstance)
        )

        // 把 iActivityTaskManagerSingleton 中的 mInstanceFiled 替换为我们的代理
        // 也就是给 iActivityTaskManagerSingleton 设置一个代理
        mInstanceFiled.set(iActivityTaskManagerSingleton, iamInstance)
    }

    private class StartActivityInvocationHandler(val any: Any) : InvocationHandler {

        //this.any 方法的执行者

        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
            Log.e("----哈哈------->", method?.name)
            return method!!.invoke(any, args)
        }

    }

}