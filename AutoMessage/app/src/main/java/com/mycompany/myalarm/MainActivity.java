package com.mycompany.myalarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends Activity {

    public static int time;
    private static EditText phoneView;
    private EditText messageView;
    private static Button done;
    public static String number;
    public static String message;
  //  public static String MY_NUMBER = "MY_NUMBER";
    public static String MY_MESSAGE = "MY_MESSAGE";
    public static String MY_TIME = "MY_TIME";

    private static final String MY_PREFERENCES = "my pref";
    public static final String PHONE = "phone";
    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneView = (EditText) findViewById(R.id.phone);

        messageView = (EditText) findViewById(R.id.message);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
       // int defaultValue = getResources().getString(PHONE);
      //  int defaultValue = getResources().getString(PHONE);
        number = sharedPreferences.getString(PHONE, "");
        message = sharedPreferences.getString(MY_MESSAGE, "");
        time = sharedPreferences.getInt(MY_TIME, -1);
        phoneView.setText(number, null);
     //   Toast.makeText(this, "Phone NUMBER " + newNumber, Toast.LENGTH_SHORT).show();
        // Select a time from a TimePickerDialog
        Button timePickerButton = (Button) findViewById(R.id.set_time);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

       // Toast.makeText(this, number + message, Toast.LENGTH_LONG).show();

        done = (Button) findViewById(R.id.result);
        done.setEnabled(false);
       // done.setTextColor(0xfffff);
        done.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = phoneView.getText().toString();
                // Log.d("test", "lenght of phone NUMber*****" + number.length());
                message = messageView.getText().toString();
                // save current phone number to Local file
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PHONE, number);
                editor.putString(MY_MESSAGE, message);
                editor.putInt(MY_TIME, time);
                editor.commit();

                Intent i = new Intent(v.getContext(), ConfirmActivity.class);
/*                i.putExtra(MY_NUMBER, number);
                i.putExtra(MY_MESSAGE, message);
                i.putExtra(MY_TIME, "" + time);*/
                if (isValidNumber(number) && !message.equals("") && message != null)
                    startActivity(i);
                else if (message.equals("") || message == null) {
                    new AlertDialog.Builder(v.getContext())
                            .setIcon(null) //R.drawable.ic_launcher)
                            .setTitle("Please Enter Message")
                            .setPositiveButton("OK", null).show();
                }
                    //Toast.makeText(v.getContext(), "Enter Your Message", Toast.LENGTH_SHORT).show();
                else {
                    new AlertDialog.Builder(v.getContext())
                            .setIcon(null) //R.drawable.ic_launcher)
                            .setTitle("Invalid Phone Number")
                            .setPositiveButton("OK", null).show();
                }
                    //Toast.makeText(v.getContext(), "Invalid Number ", Toast.LENGTH_SHORT).show();
            }

            ;

        });

    }

    public static  class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hrs, int min) {

            /* Do something with the time chosen by the user */
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int currentTime = hour * 60 * 60 + minute * 60;
            int userTime = hrs * 60 * 60 + min * 60;

            if (userTime <= currentTime) {
                // time in milliseconds
                time = (24 * 60 * 60 - (currentTime - userTime)) * 1000;
            } else {
                time = (userTime - currentTime) * 1000;
            }

            done.setEnabled(true);
            done.setTextColor(Color.parseColor("#FF6A00"));

            // set alert box message
            int hourSet = time / 1000 / 60 / 60;
            int minuteSet = (time / 1000 / 60) % 60;
            if (hourSet != 0 && minuteSet != 0) {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(null)
                        .setTitle("Message is set in  " + hourSet + " hours and "
                                + minuteSet + " minutes")
                        .setPositiveButton("OK", null).show();
            } else if (hourSet != 0 && minuteSet == 0) {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(null)
                        .setTitle("Message is set in  " + hourSet + " hours")
                        .setPositiveButton("OK", null).show();
            } else {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(null)
                        .setTitle("Message is set in  " + minuteSet + " minutes")
                        .setPositiveButton("OK", null).show();
            }
            //Toast.makeText(view.getContext(), "Message is set in  " + time/1000/60/60 + " hours and "
            //        + (time/1000/60)%60 + " minutes" , Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isValidNumber(String str) {

        if (str.length() == 10 || str.length() == 11 || str.length() == 7) {

            for (int i = 0; i < str.length(); i++) {
                if ( str.charAt(i) < 48 || str.charAt(i) > 57)
                    return false;
            }

            return true;
        }
        return false;
       /* try
        {
            Integer.parseInt(str); // parseInt change original of str???

        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        *//*if (str.length() == 10 || str.length() == 11 || str.length() == 7)
            return true;
        else*//*
            return true;*/
    }

   /* public void scheduleAlarm(View V)
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
                , 1000 * 5, PendingIntent.getBroadcast(this,1,  intent, PendingIntent.FLAG_UPDATE_CURRENT));

        Toast.makeText(this, "Alarm Scheduled for Texting every 5 seconds", Toast.LENGTH_LONG).show();

    }*/
}