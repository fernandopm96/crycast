package com.example.crycast

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.model.User

class WebsocketService : Service() {

    private val db: CryCastDatabase = CryCastDatabase.getInstance(application)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread{
            Runnable {
                run {
                    while (true){
                        Log.i("Service", "Service is running...")
                        Thread.sleep(2000)
                    }
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }
    suspend fun insertUser(){
        var user: User = User("0", "prueba", "prueba", null)
        db.userDao().insert(user)

    }

    @Nullable
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}