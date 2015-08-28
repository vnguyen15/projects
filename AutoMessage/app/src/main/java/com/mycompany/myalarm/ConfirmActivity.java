package com.mycompany.myalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        startAutoMSM(this);
        String phoneNumber = MainActivity.number; //i.getStringExtra(MainActivity.MY_NUMBER);
        String message =  MainActivity.message; // i.getStringExtra(MainActivity.MY_MESSAGE);
        int time =  MainActivity.sharedPreferences.getInt(MainActivity.MY_TIME, -1);
        TextView result = (TextView) findViewById(R.id.test);
        int hour = time/1000/60/60;
        int minute = (time/1000/60)%60;
        if (hour != 0 && minute != 0) {
            result.setText("Message will be sent to " + phoneNumber + " in  " + hour + " hours and "
                    + minute + " minutes");
        } else if (hour == 0 && minute != 0) {
            result.setText("Message will be sent to " + phoneNumber + " in  " + minute + " minutes");
        } else {
            result.setText("Message will be sent to " + phoneNumber + " in  " + hour);
        }


        TextView messageView = (TextView) findViewById(R.id.messageView);
        messageView.setText(message);


      /*  Bundle extras = intent.getExtras();
        if(extras == null) {
            phoneNumber = null;
            message = null;
        } else {
            phoneNumber= extras.getString(MainActivity.MY_NUMBER);
            message = extras.getString(MainActivity.MY_MESSAGE);
        }*/
    /*    SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
        // Show the toast  like in above screen shot
        Toast.makeText(this, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();*/

    }

    public static void startAutoMSM(Context context) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        /*intent.putExtra(MainActivity.MY_NUMBER, phoneNumber);
        intent.putExtra(MainActivity.MY_MESSAGE, message);*/
        ComponentName receiver = new ComponentName(context, AlarmReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        // create the object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        //   alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        // PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        // alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + time, pendingIntent);
        // repeat every 5 seconds
        int time = MainActivity.sharedPreferences.getInt(MainActivity.MY_TIME, -1);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()
                + time, PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));

        /*Toast.makeText(context, "Message will be sent in  " + time/1000/60/60 + " hours and "
                + (time/1000/60)%60 + " minutes" , Toast.LENGTH_SHORT).show();*/
    }

   /* public void scheduleAlarm()
    {
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
        Long time = new GregorianCalendar().getTimeInMillis()+1000;

        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers and
        //we will write the code to send SMS inside onRecieve() method pf Alarmreciever class
        Intent intent = new Intent(this, ConfirmActivity.class);//AlarmReceiver.class);
        intent.putExtra(MY_NUMBER, number);
        intent.putExtra(MY_MESSAGE, message);
        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        //   alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        // repeat every 5 seconds
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis()
                , 1000 * 5, PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));

        Toast.makeText(this, "Alarm Scheduled for Texting every 5 seconds", Toast.LENGTH_LONG).show();

    }*/
}



///// TEST AND HELP CODES////
     /*  if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                phoneNumber = null;
                message = null;
            } else {
                phoneNumber= extras.getString(MainActivity.MY_NUMBER);
                message = extras.getString(MainActivity.MY_MESSAGE);
            }
        } else {
            phoneNumber = (String) savedInstanceState.getSerializable(MainActivity.MY_NUMBER);
            message = (String) savedInstanceState.getSerializable(MainActivity.MY_MESSAGE);
        }*/
