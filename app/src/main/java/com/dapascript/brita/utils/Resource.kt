package com.dapascript.brita.utils

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 05.59
 */
sealed class Resource<T>(val data: T?  = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String?, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}
