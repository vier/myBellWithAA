//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package net.monoboy.test;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public final class PhoneStateReceiver_
    extends PhoneStateReceiver
{

    private Context context_;

    private void init_() {
        telephonyManager = ((TelephonyManager) context_.getSystemService(Context.TELEPHONY_SERVICE));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context_ = context;
        init_();
        super.onReceive(context, intent);
    }

}