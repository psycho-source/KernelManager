package com.psycho.manager.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.psycho.manager.R
import com.psycho.manager.activities.Choice
import com.psycho.manager.activities.MainActivity
import com.psycho.manager.utils.FileUtils
import com.psycho.manager.utils.JsonUtils
import com.psycho.manager.utils.PropUtils
import com.psycho.manager.utils.UpdateUtils
import java.text.SimpleDateFormat
import java.util.*

class IntroFragment : Fragment() {

    private lateinit var updateCard: MaterialCardView
    private lateinit var device: TextView
    private lateinit var psychoVersion: TextView
    private lateinit var buildType: TextView
    private lateinit var kernVer: TextView
    private lateinit var buildDate: TextView
    private lateinit var updStatus: TextView
    private lateinit var updCheckDate: TextView
    private lateinit var updLatestVersion: TextView
    private lateinit var updChannel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Choice.choice = 0
        val sharedPreferences = context?.getSharedPreferences("update", Context.MODE_PRIVATE)
        val updateSharedPreferences = context?.getSharedPreferences("update", Context.MODE_PRIVATE)
        val view = inflater.inflate(R.layout.fragment_intro, container, false)

        updateCard = view.findViewById(R.id.upd_card)
        device = view.findViewById(R.id.device_name)
        psychoVersion = view.findViewById(R.id.psycho_version)
        buildType = view.findViewById(R.id.build_type)
        kernVer = view.findViewById(R.id.kernel_version)
        buildDate = view.findViewById(R.id.build_date)
        updStatus = view.findViewById(R.id.update_status)
        updCheckDate = view.findViewById(R.id.last_check_date)
        updLatestVersion = view.findViewById(R.id.latest_version)
        updChannel = view.findViewById(R.id.update_channel)

        device.text = PropUtils.readProp("ro.product.device")
        psychoVersion.text = JsonUtils.getJsonObject(FileUtils.readFile("/proc/PsychoKernel") ?: "{}")?.getString("version") ?: "Unknown"
        buildType.text = JsonUtils.getJsonObject(FileUtils.readFile("/proc/PsychoKernel") ?: "{}")?.getString("type") ?: "Unknown"
        kernVer.text = PropUtils.kernelVersion()
        buildDate.text = SimpleDateFormat("EEE, dd MMM yyyy hh:mm aa", Locale.getDefault()).format(SimpleDateFormat("yyyyMMdd-HHmm", Locale.getDefault()).parse(JsonUtils.getJsonObject(FileUtils.readFile("/proc/PsychoKernel") ?: "")?.getString("buildtime") ?: "Unknown") ?: "") ?: "Unknown"

        updCheckDate.text = sharedPreferences?.getString("lastCheck", "Never")
        updChannel.text = sharedPreferences?.getString("channel", "Stable")
        val latestVersion = context?.getSharedPreferences("latest", Context.MODE_PRIVATE)?.getFloat("version", -1f) ?: -1f
        updLatestVersion.text = if(latestVersion == -1f) "Unknown" else latestVersion.toString()

        updStatus.text = sharedPreferences?.getString("updateStatus", "Updated")
        updateCard.strokeColor = context!!.getColor(if(sharedPreferences?.getString("updateStatus", "Updated").equals("updated", true))  R.color.colorPsycho else R.color.colorBattery)

        updStatus.text = sharedPreferences?.getString("updateStatus", "Updated")
        updateCard.strokeColor = context!!.getColor(if(sharedPreferences?.getString("updateStatus", "Updated").equals("updated", true))  R.color.colorPsycho else R.color.colorBattery)

        if((updateSharedPreferences?.getBoolean("startup", true) != false && Choice.isMainRedrawn == 1) || Choice.isMainRedrawn == 1) {
            updateUI(sharedPreferences)
        }

        (activity as MainActivity).fab.setOnClickListener {
            updateUI(sharedPreferences)
        }

        return view
    }

    private fun updateUI(sharedPreferences: SharedPreferences?) {
        Choice.isMainRedrawn = 0
        updStatus.text = context!!.getText(R.string.upd_checking)
        updateCard.strokeColor = context!!.getColor(R.color.colorStroke)
        Thread(Runnable {
            UpdateUtils.checkUpd(context!!)
            (activity as MainActivity).runOnUiThread {
                val temp = SimpleDateFormat("EEE, dd MMM yyyy hh:mm aa", Locale.getDefault()).format(Date())
                sharedPreferences?.edit()?.putString("lastCheck", temp)?.apply()
                updCheckDate.text = temp
                updStatus.text = sharedPreferences?.getString("updateStatus", "Updated")
                val latestVersion = context?.getSharedPreferences("latest", Context.MODE_PRIVATE)?.getFloat("version", -1f) ?: -1f
                updLatestVersion.text = if(latestVersion == -1f) "Unknown" else latestVersion.toString()
                updateCard.strokeColor = context!!.getColor(if(sharedPreferences?.getString("updateStatus", "Updated").equals("updated", true))  R.color.colorPsycho else R.color.colorBattery)
            }
        }).start()
    }

}
