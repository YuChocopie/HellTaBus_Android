/*
 * Created by Leo on 2021. 09. 26 ..
 */
package com.mashup.healthyup.domain

// TODO : kotlin.Result 를 사용할 수는 없을까? runCatching 을 쓰는 방법으로 ..
sealed class Result<out R> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null

fun <T> Result<T>.getValue(): T {
    return if (this is Result.Success) {
        this.data
    } else {
        throw (this as Result.Error).exception
    }
}
