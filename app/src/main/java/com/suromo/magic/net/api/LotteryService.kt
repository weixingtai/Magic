package com.suromo.magic.net.api

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.suromo.magic.MainApplication.Companion.mApplicationContext
import com.suromo.magic.net.interceptor.HeadInterceptor
import com.suromo.magic.net.response.Lotteries
import com.suromo.magic.net.response.LotteryResponse
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23
 * desc   :
 */
interface LotteryService {

    @GET("TbStat/GetMarkSixHistoryByYear")
    suspend fun getLotteryHistoryByYear(
        @Query("lotteryInfoId") infoId: Int = 22,
        @Query("year") year: Int = 2022
    ): LotteryResponse<Lotteries>

    @GET("TbStat/GetMarkSixHistoryByYear")
    suspend fun getLotteryRecommendByYear(
        @Query("lotteryInfoId") infoId: Int = 22,
        @Query("year") year: Int = 2021
    ): LotteryResponse<Lotteries>



    companion object {
        private const val BASE_URL = "https://tb.tbxla.com/"

        fun create(): LotteryService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .cache(Cache(File(mApplicationContext.cacheDir, "magic_cache"), 10 * 1024 * 1024))
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(mApplicationContext)))
                .addInterceptor(HeadInterceptor())
                .addInterceptor(logger)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LotteryService::class.java)
        }
    }

}