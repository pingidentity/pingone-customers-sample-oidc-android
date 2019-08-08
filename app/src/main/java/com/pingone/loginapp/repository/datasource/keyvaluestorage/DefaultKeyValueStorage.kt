package com.pingone.loginapp.repository.datasource.keyvaluestorage

import com.orhanobut.hawk.Hawk

//TODO: implement kv methods
class DefaultKeyValueStorage : KeyValueStorage {

    override val token: String?
        get() {
            return Hawk.get(SESSION_ID)
        }


    override fun onUserLoggedIn(sessionId: String) {
        Hawk.put(SESSION_ID, sessionId)
    }

    override fun isUserLoggedIn(): Boolean {
        return token != null
    }

    override fun logout() {
        Hawk.delete(SESSION_ID)
        Hawk.delete(TOKEN)
    }

    private companion object {
        const val SESSION_ID = "session_id"
        const val TOKEN = "token"
    }
}