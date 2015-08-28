package com.mycompany.myalarm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO Auto-generated method stub
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
            if (MainActivity.sharedPreferences.getInt(MainActivity.MY_TIME, -1) != -1)
                ConfirmActivity.startAutoMSM(context);
        } else {
            if (MainActivity.sharedPreferences.getInt(MainActivity.MY_TIME, -1) != -1) {
                String phoneNumber = MainActivity.sharedPreferences.getString(MainActivity.PHONE, ""); //intent.getStringExtra(MainActivity.MY_NUMBER);
                String message = MainActivity.sharedPreferences.getString(MainActivity.MY_MESSAGE, ""); //intent.getStringExtra(MainActivity.MY_MESSAGE);
                // here you can start an activity or service depending on your need
                // for ex you can start an activity to vibrate phone or to ring the phone
                // String phoneNumberReciver=""; //"14252339515";
                //  String phoneNumberReciver="14152728053";// phone number to which SMS to be send
                // Intent intent1 =  getIntent();
      /*  Bundle extras = intent.getExtras();
        if(extras == null) {
            phoneNumber = null;
            message = null;
        } else {
            phoneNumber= extras.getString(MainActivity.MY_NUMBER);
            message = extras.getString(MainActivity.MY_MESSAGE);
        }*/
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNumber, null, message, null, null);
                // Show the toast  like in above screen shot
                Toast.makeText(context, "Message has been Sent", Toast.LENGTH_LONG).show();
            }
        }
        ComponentName receiver = new ComponentName(context, AlarmReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

    }

}