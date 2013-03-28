package net.monoboy.test.br;

import net.monoboy.test.RingActivity_;

import com.googlecode.androidannotations.annotations.EReceiver;
import com.googlecode.androidannotations.annotations.SystemService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

@EReceiver
public class PhoneStateReceiver extends BroadcastReceiver {

	@SystemService
	TelephonyManager telephonyManager;
	
	@Override
	public void onReceive(Context ctx, Intent intent) {		
		MyBellPhoneStateListener myBellPhoneStateListener = new MyBellPhoneStateListener();
		telephonyManager.listen(myBellPhoneStateListener, PhoneStateListener.LISTEN_SERVICE_STATE);
		telephonyManager.listen(myBellPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		
		Log.d("vier", intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
		
		if (TelephonyManager.CALL_STATE_RINGING == telephonyManager.getCallState()) {
			callRingActivity(ctx, intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
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
