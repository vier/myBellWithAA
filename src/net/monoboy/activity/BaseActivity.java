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
		// ���ݽ� �α׷� �˸�
		builder.penaltyLog();
		// ���ݽ� dropbox �� ����
		builder.penaltyDropBox();
		// ���ݽ� ���̾�α׷� �˸�
		builder.penaltyDialog();
		// ���ݽ� ��������
		builder.penaltyDeath();
		// ��Ʈ��ũ ��� ���ݽ� �������� , �ݵ�� detectNetwork() �� Ȱ��ȭ �Ǿ� �־����.
		builder.penaltyDeathOnNetwork(); // api level 11
		// ���ݽ� ȭ�鿡 ���� �簢�� Spash�� �˸�.
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
