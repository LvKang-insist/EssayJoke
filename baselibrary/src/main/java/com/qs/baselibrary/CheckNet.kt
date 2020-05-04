package com.qs.baselibrary

/**
 * Target：FIELD:作用于函数，方法
 * Retention：RUNTIME:作用于运行时
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class CheckNet() {
}