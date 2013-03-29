package net.monoboy.core;

import android.widget.ImageView;

public class GlobalHolder {
	private static ImageView callReceiverImageView;

	public static ImageView getCallReceiverImageView() {
		return callReceiverImageView;
	}

	public static void setCallReceiverImageView(ImageView callReceiverImageView) {
		GlobalHolder.callReceiverImageView = callReceiverImageView;
	}
}
