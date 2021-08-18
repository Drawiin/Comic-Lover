package com.example.comiclover.utils.extensions

fun String.isValidJson() = startsWith("{") or startsWith("[")