package com.psycho.manager.utils

import android.content.Context

class ProfileUtils {

    companion object {

        @JvmStatic
        fun applyProfile(context: Context) {

            val sharedPreferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE)
            when(sharedPreferences.getInt("current", 1)) {
                0 -> {
                    //Battery Profile here
                    //Little
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_governor", "conservative")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_max_freq", "1401600")

                    //Big
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_governor", "conservative")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_max_freq", "1113600")

                    //Cores Offline
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu2/online", "1")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu3/online", "0")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu6/online", "0")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu7/online", "0")

                    //GPU
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_governor", "powersave")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_min_clock", "160")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_max_clock", "266")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_active", "Y")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idlewait", "20")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idleworkload", "5000")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_downdifferential", "35")

                    //Misc
                    FileUtils.writeFile("/sys/module/workqueue/parameters/power_efficient", "Y")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vmax_mv", "0")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vtg_level", "0")
                }
                1 -> {
                    //Psycho profile here
                    //Little
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_governor", "interactive")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_max_freq", "1536000")

                    //Big
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_governor", "interactive")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_max_freq", "1747200")

                    //Cores Offline
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu2/online", "0")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu3/online", "0")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu6/online", "0")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu7/online", "0")

                    //GPU
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_governor", "msm-adreno-tz")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_min_clock", "160")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_max_clock", "370")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_active", "Y")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idlewait", "20")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idleworkload", "5000")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_downdifferential", "35")

                    //Misc
                    FileUtils.writeFile("/sys/module/workqueue/parameters/power_efficient", "Y")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vmax_mv", "1866")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vtg_level", "1866")
                }
                2 -> {
                    //Performance profile here
                    //Little
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_governor", "interactive")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_max_freq", "1747200")

                    //Big
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_governor", "interactive")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_max_freq", "2150400")

                    //Cores Offline
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu2/online", "1")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu3/online", "1")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu6/online", "1")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu7/online", "1")

                    //GPU
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_governor", "msm-adreno-tz")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_min_clock", "160")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_max_clock", "430")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_active", "Y")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idlewait", "25")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idleworkload", "3000")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_downdifferential", "25")

                    //Misc
                    FileUtils.writeFile("/sys/module/workqueue/parameters/power_efficient", "Y")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vmax_mv", "1866")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vtg_level", "1866")
                }
                3 -> {
                    //Gaming profile here
                    //Little
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_governor", "performance")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy0/scaling_max_freq", "1747200")

                    //Big
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_governor", "performance")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_min_freq", "300000")
                    FileUtils.writeFile("/sys/devices/system//cpu/cpufreq/policy4/scaling_max_freq", "2150400")

                    //Cores Offline
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu2/online", "1")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu3/online", "1")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu6/online", "1")
                    FileUtils.writeFile("/sys/devices/system/cpu/cpu7/online", "1")

                    //GPU
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_governor", "performance")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_min_clock", "160")
                    FileUtils.writeFile("/sys/kernel/gpu/gpu_max_clock", "430")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_active", "Y")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idlewait", "25")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_idleworkload", "3000")
                    FileUtils.writeFile("/sys/module/adreno_idler/parameters/adreno_idler_downdifferential", "25")

                    //Misc
                    FileUtils.writeFile("/sys/module/workqueue/parameters/power_efficient", "N")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vmax_mv", "1866")
                    FileUtils.writeFile("/sys/class/timed_output/vibrator/vtg_level", "1866")
                }
                else -> {
                    //Custom, load from file maybe?
                }
            }

        }

    }

}