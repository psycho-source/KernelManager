package com.psycho.manager.utils

import android.content.Context
import android.util.Log

class UpdateUtils {

    companion object {

        @JvmStatic
        fun checkUpd(context: Context) {
            val obj = JsonUtils.getJsonObject(FileUtils.readFile("/proc/PsychoKernel") ?: "{}")
            val device = obj?.getString("kernel-name") ?: "Generic"
            val channel = context.getSharedPreferences("update", Context.MODE_PRIVATE).getString("channel", "Stable") ?: "Generic"
            val type = obj?.getString("type") ?: "Generic"
            val version = obj?.getString("version") ?: "Generic"
            if (!(device.equals("Generic", true) || channel.equals("Generic", true) || type.equals("Generic", true) || version.equals("Generic", true)))
                getUpdate(context, device, channel, type, version)
        }

        @JvmStatic
        fun getUpdate(context: Context, device: String, channel: String, type: String, version: String) {
            val updateURL = "https://raw.githubusercontent.com/psycho-source/KernelUpdates/master/$device/$channel/$type/update.json"
            val updateObject = JsonUtils.getJsonObject(NetworkUtils.fetchJSON(updateURL))
            if(updateObject?.getString("version")?.toFloat() ?: -1f > version.toFloat()) {
                if (!(context.getSharedPreferences("update", Context.MODE_PRIVATE).edit().putString("updateStatus", "Update Available").commit()))
                    Log.e("TAG", "getUpdate: Failed to write to Shared Preferences")
            } else {
                if (!(context.getSharedPreferences("update", Context.MODE_PRIVATE).edit().putString("updateStatus", "Updated").commit()))
                    Log.e("TAG", "getUpdate: Failed to write to Shared Preferences")
            }
            val saveLatest = context.getSharedPreferences("latest", Context.MODE_PRIVATE).edit()
            saveLatest.putFloat("version", updateObject?.getString("version")?.toFloat() ?: -1f)
            saveLatest.putString("url", updateObject?.getString("url"))
            saveLatest.putInt("size", updateObject?.getInt("size") ?: 0)
            saveLatest.putString("md5", updateObject?.getString("md5"))
            saveLatest.putString("sha256", updateObject?.getString("sha256"))
            saveLatest.putString("changelog", updateObject?.getString("changelog"))
            saveLatest.apply()
        }

    }

}