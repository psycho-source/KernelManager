package com.psycho.manager.utils

import android.util.Log
import com.topjohnwu.superuser.Shell
import java.io.FileReader
import java.io.FileWriter
import kotlin.Exception

class FileUtils {

    companion object {

        @JvmStatic
        fun readFile(path: String): String? {
            var res: String? = null
            var fileReader: FileReader? = null
            try {
                fileReader = FileReader(path)
                res = fileReader.readText()
            }catch (e: Exception) {
                Log.e("FileUtils", "readFile: Cannot read the file ${path}")
                res = null
            } finally {
                fileReader?.close()
            }
            return res
        }

        @JvmStatic
        fun writeFile(path: String, content: String) {
            var fileWriter: FileWriter? = null
            try {
                fileWriter = FileWriter(path)
                fileWriter.write(content)
            } catch (e: Exception) {
                Log.e("FileUtils", "writeFile: Cannot write to the file ${path}")
            } finally {
                fileWriter?.close()
            }
        }

        @JvmStatic
        fun setFilePermissions(): Shell.Job {
            return Shell.su(
                    "chmod 0666 /sys/class/timed_output/vibrator/vtg_level",
                    "chmod 0666 /sys/class/timed_output/vibrator/vmax_mv",
                    "chmod 0666 /sys/class/misc/boeffla_wakelock_blocker/wakelock_blocker",
                    "chmod 0666 /sys/devices/platform/kcal_ctrl.0/kcal",
                    "chmod 0666 /sys/devices/platform/kcal_ctrl.0/kcal_val",
                    "chmod 0666 /sys/devices/platform/kcal_ctrl.0/kcal_cont",
                    "chmod 0666 /sys/devices/platform/kcal_ctrl.0/kcal_hue",
                    "chmod 0666 /sys/devices/platform/kcal_ctrl.0/kcal_enable",
                    "chmod 0666 /sys/module/workqueue/parameters/power_efficient",
                    "chmod 0666 /sys/devices/system/cpu/cpu2/online",
                    "chmod 0666 /sys/devices/system/cpu/cpu3/online",
                    "chmod 0666 /sys/devices/system/cpu/cpu6/online",
                    "chmod 0666 /sys/devices/system/cpu/cpu7/online",
                    "chmod 0666 /sys/devices/system/cpu/cpufreq/policy0/scaling_governor",
                    "chmod 0666 /sys/devices/system/cpu/cpufreq/policy0/scaling_min_freq",
                    "chmod 0666 /sys/devices/system/cpu/cpufreq/policy0/scaling_max_freq",
                    "chmod 0666 /sys/devices/system/cpu/cpufreq/policy4/scaling_governor",
                    "chmod 0666 /sys/devices/system/cpu/cpufreq/policy4/scaling_min_freq",
                    "chmod 0666 /sys/devices/system/cpu/cpufreq/policy4/scaling_max_freq",
                    "chmod 0666 /sys/kernel/gpu/gpu_governor",
                    "chmod 0666 /sys/kernel/gpu/gpu_min_clock",
                    "chmod 0666 /sys/kernel/gpu/gpu_max_clock",
                    "chmod 0666 /sys/module/adreno_idler/parameters/adreno_idler_active",
                    "chmod 0666 /sys/module/adreno_idler/parameters/adreno_idler_idlewait",
                    "chmod 0666 /sys/module/adreno_idler/parameters/adreno_idler_idleworkload",
                    "chmod 0666 /sys/module/adreno_idler/parameters/adreno_idler_downdifferential"
            )
        }

    }

}