package com.example.comiclover.network.util.extensions

fun String.isValidJson() = startsWith("{") or startsWith("[")