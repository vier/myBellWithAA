package net.monoboy.br;

import net.monoboy.R;
import net.monoboy.activity.RingActivity_;
import net.monoboy.core.GlobalHolder;
import net.monoboy.util.ViewUnbindHelper;

import com.googlecode.androidannotations.annotations.EReceiver;
import com.googlecode.androidannotations.annotations.SystemService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

@EReceiver
public class PhoneStateReceiver extends BroadcastReceiver {

	@SystemService
	TelephonyManager telephonyManager;

	@SystemService
	WindowManager windowManager;
	
	private ImageView incomingCallReceivingImage;

	@Override
	public void onReceive(Context ctx, Intent intent) {
		MyBellPhoneStateListener myBellPhoneStateListener = new MyBellPhoneStateListener();
		telephonyManager.listen(myBellPhoneStateListener, PhoneStateListener.LISTEN_SERVICE_STATE);
		telephonyManager.listen(myBellPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

		Log.d("vier", intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) + "  "  + telephonyManager.getCallState());

		if (TelephonyManager.CALL_STATE_RINGING == telephonyManager.getCallState()) {
			
			if (GlobalHolder.getCallReceiverImageView() == null) {
				GlobalHolder.setCallReceiverImageView(new ImageView(ctx.getApplicationContext()));
			}
			incomingCallReceivingImage = GlobalHolder.getCallReceiverImageView();
			incomingCallReceivingImage.setImageResource(R.drawable.cgh);
			incomingCallReceivingImage.setScaleType(ScaleType.FIT_XY);

			WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
					WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
					WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, PixelFormat.TRANSLUCENT);
			params.gravity = Gravity.TOP | Gravity.RIGHT;
			windowManager.addView(incomingCallReceivingImage, params);

			callRingActivity(ctx,intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
		} else {
			Log.d("vier", "remove : " + telephonyManager.getCallState());
			if (GlobalHolder.getCallReceiverImageView() != null) {
				try {
					windowManager.removeView(GlobalHolder.getCallReceiverImageView());
					ViewUnbindHelper.unbindReferences(GlobalHolder.getCallReceiverImageView());
				} catch (Exception e) {
					//ignore
				}
			}
		}
	}

	private static class MyBellPhoneStateListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

		}

		@Override
		public void onServiceStateChanged(ServiceState serviceState) {

		}
	}

	private void callRingActivity(final Context ctx, final String incomingPhoneNumber) {
		final Intent newRingIntent = new Intent(ctx.getApplicationContext(), RingActivity_.class);
		newRingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

		new Thread() {
			public void run() {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// ignore
				} finally {
					newRingIntent.putExtra("incoming", incomingPhoneNumber);
					ctx.getApplicationContext().startActivity(newRingIntent);
					
					
				}
			}
		}.start();
	}
}
