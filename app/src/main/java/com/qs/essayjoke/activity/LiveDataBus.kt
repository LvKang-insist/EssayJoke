package com.qs.essayjoke.activity

import android.util.Log
import androidx.lifecycle.*

/**
 * @name LiveDataBus
 * @package com.qs.essayjoke.activity
 * @author 345 QQ:1831712732
 * @time 2020/5/29 22:47
 * @description https://www.jianshu.com/p/f69e5f0dba9b
 */

object LiveDataBus {

    const val START_VERSION = -1

    private val liveData: BusLiveData<Any> = BusLiveData()


    fun with(): BusLiveData<Any> {
        return liveData
    }

    class BusLiveData<T> : MutableLiveData<T>() {
        var mVersion = START_VERSION
        //+1

        /**
         * 普通事件
         */
        override fun setValue(value: T) {
            mVersion++
            super.setValue(value)
        }

        override fun postValue(value: T) {
            mVersion++
            super.postValue(value)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            owner.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        Log.e("-------->", "销毁")
                        removeObserver(observer)
                    }
                }
            })
            super.observe(owner, ObserverWrapper(observer, this))
        }
    }

    /**
     * Observer 的包装类
     * 在内部通过判断 version 来确定是否通知观察者
     */
    class ObserverWrapper<T>(
        private val observer: Observer<in T>,
        private val liveData: BusLiveData<T>
    ) :
        Observer<T> {
        private var mLastVersion = liveData.mVersion

        override fun onChanged(t: T) {
            if (mLastVersion >= liveData.mVersion) {
                return
            }
            mLastVersion = liveData.mVersion
            observer.onChanged(t)
        }

    }
}