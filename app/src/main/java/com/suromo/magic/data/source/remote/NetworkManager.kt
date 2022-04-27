package com.suromo.magic.data.source.remote

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import com.orhanobut.logger.Logger
import com.suromo.common.data.source.remote.BaseNetworkManager
import com.suromo.common.data.source.remote.interceptor.CacheInterceptor
import com.suromo.common.util.AppUtil
import com.suromo.magic.data.source.remote.interceptor.HeadInterceptor
import com.suromo.magic.data.source.remote.interceptor.TokenExpireInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time  : 2022/04/26
 * desc  : Retrofit网络类
 */

//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
val networkService: NetworkService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkManager.instance.getService(NetworkService::class.java, NetworkService.BASE_URL)
}

class NetworkManager : BaseNetworkManager() {

    companion object {
        val instance: NetworkManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkManager()
        }
    }

    /**
     * 实现重写父类的setHttpClientBuilder方法，
     * 在这里可以添加拦截器，可以对 OkHttpClient.Builder 做任意操作
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(Cache(File(AppUtil.getApplication()?.cacheDir, "magic_cache"), 10 * 1024 * 1024))
            //添加Cookies自动持久化
            cookieJar(cookieJar)
            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            addInterceptor(HeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
            addInterceptor(CacheInterceptor())
            addInterceptor(TokenExpireInterceptor())
            // 日志拦截器
            .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Logger.d(message)
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY))
            //超时时间 连接、读、写
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder
    }

    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }

    private val cookieJar: PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(AppUtil.getApplication()))
    }

}