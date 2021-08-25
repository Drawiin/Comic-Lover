package com.drawiin.comiclover.network.util.extensions

fun String.isValidJson() = startsWith("{") or startsWith("[")