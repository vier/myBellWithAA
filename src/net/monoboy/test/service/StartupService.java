package net.monoboy.test.service;

import com.googlecode.androidannotations.annotations.EService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

@EService
public class StartupService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.d("vier", "StartupService : onStartCommand : " + intent + " | " + flags + " | " + startId);
		return START_REDELIVER_INTENT;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("vier", "StartupService : onCreate");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("vier", "StartupService : onDestroy");
	}

}
