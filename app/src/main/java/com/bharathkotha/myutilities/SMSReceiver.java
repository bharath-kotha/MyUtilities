package com.bharathkotha.myutilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMS_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sp = context.getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (intent.getAction() == SMS_RECEIVED) {
            Bundle dataBundle = intent.getExtras();
            if (dataBundle != null) {
                String format = dataBundle.getString("format");
                Object[] myPDUs = (Object[]) dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[myPDUs.length];
                Log.i(TAG, "" + myPDUs.length);
                for (int i = 0; i < myPDUs.length; i++) {
                    message[i] = SmsMessage.createFromPdu((byte[]) myPDUs[i], format);
                    editor.putString("Message", message[i].getMessageBody());
                }
                editor.commit();
            }
        }
    }
}