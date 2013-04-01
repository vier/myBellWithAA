package net.monoboy.core;

import android.content.Context;
import android.widget.ImageView;

public class GlobalHolder {
	private static ImageView callReceiverImageView;
	private static Context applicationContext;
	
	public static void init(Context context) {
		GlobalHolder.applicationContext = context;
	}

	public static ImageView getCallReceiverImageView() {
		return callReceiverImageView;
	}

	public static void setCallReceiverImageView(ImageView callReceiverImageView) {
		GlobalHolder.callReceiverImageView = callReceiverImageView;
	}

	public static Context getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(Context applicationContext) {
		GlobalHolder.applicationContext = applicationContext;
	}
	
	
}
