package net.monoboy.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

public abstract class BaseActivity extends Activity {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder();
		builder.detectCustomSlowCalls(); // api level 11
		builder.detectNetwork();
		builder.detectDiskReads();
		builder.detectDiskWrites();
		builder.detectNetwork();
		builder.penaltyLog();
		builder.penaltyDropBox();
		builder.penaltyDialog();
		builder.penaltyDeath();
		builder.penaltyDeathOnNetwork(); // api level 11
		builder.penaltyFlashScreen(); // api level 11
		StrictMode.setThreadPolicy(builder.build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectActivityLeaks() // api level // 11
				.detectLeakedClosableObjects() // api level 11
				.setClassInstanceLimit(MainActivity_.class, 1) // api level 11
				.penaltyLog().penaltyDeath().penaltyDropBox().build());
	}
}
