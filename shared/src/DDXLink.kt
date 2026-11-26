package me.y9san9.ddxofflineqr

import kotlin.io.encoding.Base64
import kotlin.time.Clock
import kotlin.time.Instant

object DDXLink {
    sealed interface Parse {
        data object Fail : Parse
        data class Ok(val data: Data) : Parse
    }

    fun parse(string: String): Parse {
        if (!string.startsWith("DD2")) {
            return Parse.Fail
        }
        val segments = string.trim().split("_")
        if (segments.size != 4) {
            return Parse.Fail
        }
        val (_, userIdString, magicHash) = segments
        val userId = userIdString.toLongOrNull() ?: return Parse.Fail
        val data = Data(userId, magicHash)
        return Parse.Ok(data)
    }

    fun at(data: Data, instant: Instant): String {
        val seconds = instant.epochSeconds.toInt() % 5 * 5
        val secondsBytes = seconds.reinterpretAsByteArrayBigEndian()
        val secondsBase64 = Base64.encode(secondsBytes)
        return "DD2_${data.userId}_${data.magicHash}_$secondsBase64"
    }

    fun Int.reinterpretAsByteArrayBigEndian(): ByteArray = ByteArray(4) { i ->
        ((this shr (8 * (4 - i - 1))) and 0xFF).toByte()
    }

    data class Data(val userId: Long, val magicHash: String)
}
