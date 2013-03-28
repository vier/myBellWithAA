package net.monoboy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

public abstract class BaseActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder();
		builder.detectCustomSlowCalls(); // api level 11
		builder.detectNetwork();
		builder.detectDiskReads();
		builder.detectDiskWrites();
		builder.detectNetwork();
		// 위반시 로그로 알림
		builder.penaltyLog();
		// 위반시 dropbox 에 저장
		builder.penaltyDropBox();
		// 위반시 다이얼로그로 알림
		builder.penaltyDialog();
		// 위반시 강제종료
		builder.penaltyDeath();
		// 네트워크 사용 위반시 강제종료 , 반드시 detectNetwork() 가 활성화 되어 있어야함.
		builder.penaltyDeathOnNetwork(); // api level 11
		// 위반시 화면에 빨간 사각형 Spash로 알림.
		builder.penaltyFlashScreen(); // api level 11
		StrictMode.setThreadPolicy(builder.build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectActivityLeaks() // api level
																	// 11
				.detectLeakedClosableObjects() // api level 11
				.setClassInstanceLimit(MainActivity_.class, 1) // api level 11
				.penaltyLog().penaltyDeath().penaltyDropBox().build());
	}
}
