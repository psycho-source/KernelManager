package com.psycho.manager.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class OnBoot: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startForegroundService(Intent(context, BootService::class.java))
    }

}