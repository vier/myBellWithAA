package net.monoboy.br;

import com.googlecode.androidannotations.annotations.EReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

@EReceiver
public class SmsReceiver extends BroadcastReceiver {

	private static final String SMS_EXTRA_NAME = "pdus";

	@Override
	public void onReceive(Context context, Intent intent) {
		// Get the SMS map from Intent
		Bundle extras = intent.getExtras();

		String messages = "";

		if (extras != null) {
			// Get received SMS array
			Object[] smsExtra = (Object[]) extras.get(SMS_EXTRA_NAME);

			for (Object aSmsExtra : smsExtra) {
				SmsMessage sms = SmsMessage.createFromPdu((byte[]) aSmsExtra);

				String body = sms.getMessageBody();
				String address = sms.getOriginatingAddress();

				messages += "SMS from " + address + " :\n";
				messages += body + "\n";
			}
			
			Toast.makeText(context, messages, Toast.LENGTH_SHORT).show();
		}
	}
}
