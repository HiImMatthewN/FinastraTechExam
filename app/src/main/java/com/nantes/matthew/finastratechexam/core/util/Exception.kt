package com.nantes.matthew.finastratechexam.core.util

import java.lang.Exception
import java.net.UnknownHostException

fun Exception.toReadableMessage():String{
    return when(this){
        is UnknownHostException -> "No Internet Connection"
        else -> this.localizedMessage ?: "Unknown Error"
    }
}