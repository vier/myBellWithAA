package net.monoboy.br;

import net.monoboy.service.StartupService_;

import com.googlecode.androidannotations.annotations.EReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

@EReceiver
public class StartupReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("vier", "StartupReceiver : onReceive");
		context.startService(new Intent(context, StartupService_.class));
	}

}
