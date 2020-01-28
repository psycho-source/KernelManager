package com.psycho.manager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.psycho.manager.R
import com.psycho.manager.utils.FileUtils
import com.psycho.manager.utils.ProfileUtils
import com.topjohnwu.superuser.Shell

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        FileUtils.setFilePermissions().submit {
            //Apply profile on start
            ProfileUtils.applyProfile(this);
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

}