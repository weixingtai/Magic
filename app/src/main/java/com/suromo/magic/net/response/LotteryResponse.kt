package com.suromo.magic.net.response

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/24
 * desc   :
 */
data class LotteryResponse<T>(val code: Int, val msg: String, val body: T) : BaseResponse<T>() {

    override fun isSuccess() = code == 0

    override fun getResponseCode() = code

    override fun getResponseData() = body

    override fun getResponseMsg() = msg

}