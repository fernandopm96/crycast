package com.example.crycast

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable
import androidx.compose.runtime.collectAsState
import com.example.crycast.database.CryCastDatabase
import com.example.crycast.repository.UserRepository
import com.example.crycast.services.UsersApiService
import java.util.concurrent.Flow

class WebsocketService : Service() {

    lateinit var context: Context
    lateinit var db: CryCastDatabase
    lateinit var apiService: UsersApiService

    lateinit var idUser: String

    override fun onCreate() {
        super.onCreate()
        context = this
        db = CryCastDatabase.getInstance(context)
        apiService = UsersApiService.getInstance()
    }

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



    @Nullable
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}