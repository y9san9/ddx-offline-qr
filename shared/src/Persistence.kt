package me.y9san9.ddxofflineqr

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

object Persistence {
    private val settings = Settings()

    fun save(data: DDXLink.Data) {
        settings["userId"] = data.userId
        settings["magicHash"] = data.magicHash
    }

    fun load(): DDXLink.Data? {
        val userId: Long = settings["userId"] ?: return null
        val magicHash: String = settings["magicHash"] ?: return null
        return DDXLink.Data(userId, magicHash)
    }
}
