package com.drawiin.network.util.extensions

fun String.isValidJson() = startsWith("{") or startsWith("[")