package com.example.comiclover.network.util

import com.example.comiclover.core.constants.PRIVATE_KEY_NAME
import com.example.comiclover.core.constants.PUBLIC_KEY_NAME
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Named

class Authenticator @Inject constructor(
    @Named(PRIVATE_KEY_NAME)
    private val privateKey: String,
    @Named(PUBLIC_KEY_NAME)
    private val publicKey: String
) {

    val credentials: Credentials
        get() {
            val timeStamp = System.currentTimeMillis().toInt()
            return Credentials(
                hash = generateHashMD5("$timeStamp$privateKey$publicKey"),
                apiKey = publicKey,
                timeStamp = timeStamp
            )
        }

    private fun generateHashMD5(
        string: String
    ) = string.toByteArray()
        .let { bytes -> MessageDigest.getInstance("MD5").digest(bytes) }
        .let { digestedBytes -> BigInteger(1, digestedBytes) }
        .toString(16)
        .padStart(32, '0')
}