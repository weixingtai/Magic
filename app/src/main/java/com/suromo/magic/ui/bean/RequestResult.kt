package com.suromo.magic.ui.bean

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
sealed class RequestResult<out R> {
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()
}

fun <T> RequestResult<T>.successOr(fallback: T): T {
    return (this as? RequestResult.Success<T>)?.data ?: fallback
}