package com.psycho.manager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.psycho.manager.R
import com.psycho.manager.utils.FileUtils
import com.topjohnwu.superuser.Shell

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        //Commands to run on app launch
        FileUtils.setFilePermissions().submit {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

}