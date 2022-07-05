package com.example.mol12345

import android.content.Context
import android.content.SharedPreferences
import com.example.mol12345.PreferenceHelper.set
import com.example.mol12345.PreferenceHelper.get

class SharedManager(context: Context) {

    private val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(context)

    fun saveCurrentData(data1: Data1) {
        prefs["id"] = data1.id
        prefs["name"] = data1.name
        prefs["nick"] = data1.nick
        prefs["phone_number"] = data1.number
    }

    fun getCurrentData(): Data1 {
        return Data1(
            id = prefs["id", 0],
            name = prefs["name", ""],
            nick = prefs["nick", ""],
            number = prefs["phone_number", ""],
        )
    }

}
