package com.mycompany.myservice;

import android.app.AlarmManager;
import  android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;


public class AlarmService extends IntentService {

    public final static String valuePassIn = "Start Alarm";
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public AlarmService() {
        super("AlarmService");

    }

   /* @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }*/
    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {


        Toast.makeText(this, "start service", Toast.LENGTH_LONG).show();
        // Normally we would do some work here, like download a file.

       // do something here with the intent from the alarm manager below

        // This is here for background service only, where we don't want to see anything
        // usually good for data transfer


    }

    public static void setServiceAlarm(Context context, boolean isOn, long time) { //}, long minute) {

        Intent i = new Intent(context, MyAlarm.class);
       // i.putExtra(valuePassIn, "true");


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
      //  long time = new GregorianCalendar().getTimeInMillis()+1000;
        //long time = 1000 * 60 * minute + 1000 * 60 * 60 * hour;

        if (isOn) {
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+time, pendingIntent); // repeat every 5 seconds
          //  Toast.makeText(context, "Alarm Clock STARTED", Toast.LENGTH_LONG).show();
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

}
