package com.suromo.magic.data.source.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time  : 2022/04/2022/4/26
 * desc  :
 */
interface NetworkService {

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }

    /**
     * 登录
     */
//    @FormUrlEncoded
//    @POST("user/login")
//    suspend fun login(
//        @Field("username") username: String,
//        @Field("password") pwd: String
//    ): ApiResponse<UserInfo>
}