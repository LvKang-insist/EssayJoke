package com.qs.baselibrary.ioc

/**
 * Target：FIELD:作用于字段
 * Retention：RUNTIME:作用于运行时
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewById(val value: Int) {
}