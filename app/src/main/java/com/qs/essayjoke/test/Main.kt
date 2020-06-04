package com.qs.essayjoke.test

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


fun main() {
    val testDemo = TestDemo()

    val proxy = Proxy.newProxyInstance(
        DemoListener::class.java.classLoader,
        arrayOf(DemoListener::class.java),
        CallBack(testDemo)
    ) as DemoListener

    proxy.demo("345")
}

class CallBack(val any: Any?) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        println("代理前 -------->")
        if (any == null) {
            return null
        }
        val invoke = method?.invoke(any, args?.get(0))
        println("完成代理 -------->")
        return invoke
    }
}
