package com.mycompany.myservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    public static final String HOURS = "hours";
    public static final String MINUTES = "minutes";
    private static Button myStartService;
    private Button myStopService;
    private MediaPlayer player;
    private static int time;
    public static String path;
    public static String PREF_PATH = "my_path";
    public static String song;
    private static Boolean startButton = false;
    public static Boolean alreadyPickSong = false;
    public static String PICK_YET = "pick_song";
    public static SharedPreferences mySongPref;
    public static String SONG_PREF = "song_preferences";
    public static String MY_SONG = "my_song";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySongPref = getSharedPreferences(SONG_PREF, Context.MODE_PRIVATE);
        song = mySongPref.getString(MY_SONG, "");
        path = mySongPref.getString(PREF_PATH, "");

        alreadyPickSong = mySongPref.getBoolean(PICK_YET, false);
        if (alreadyPickSong) {
            new AlertDialog.Builder(this)
                    .setIcon(null)
                    .setTitle(song + " has been selected ")
                    .setPositiveButton("OK", null).show();
        }

        // show current song
        TextView currentSong = (TextView) findViewById(R.id.current_song);
        if (alreadyPickSong)
            currentSong.setText(song);
        else
            currentSong.setText("Default Song");
        // select a song
        Button musicPicker = (Button) findViewById(R.id.music_picker);
        musicPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PickSongActivity.class));

            }
        });

        /*myStartService = (Button) findViewById(R.id.start_service);
        if (!startButton)
            myStartService.setEnabled(false);
        else
            myStartService.setEnabled(true);

        myStartService.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Start the service
            AlarmService.setServiceAlarm(v.getContext(), true, time); //hour, minute);  // Don't call "this" , it is not context
            myStartService.setEnabled(false);

            ComponentName receiver = new ComponentName(v.getContext(), MyAlarm.class);
            PackageManager pm = v.getContext().getPackageManager();
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
        }
        });*/

        // Select a time from a TimePickerDialog
        Button timePickerButton = (Button) findViewById(R.id.set_time);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");

            }
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

            hour = time / 1000 / 60 / 60;
            minute = (time / 1000 / 60) % 60;

            if (hour != 0 && minute != 0) {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(null)
                        .setTitle("Alarm will start in " + hour + " hours and "
                                + minute + " minutes")
                        .setPositiveButton("OK", null).show();
            } else if (hour != 0 && minute == 0) {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(null)
                        .setTitle("Alarm will start in " + hour + " hours")
                        .setPositiveButton("OK", null).show();
            } else {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(null)
                        .setTitle("Alarm will start in " + minute + " minutes")
                        .setPositiveButton("OK", null).show();
            }

            // Start the service
            AlarmService.setServiceAlarm(view.getContext(), true, time); //hour, minute);  // Don't call "this" , it is not context
            ComponentName receiver = new ComponentName(view.getContext(), MyAlarm.class);
            PackageManager pm = view.getContext().getPackageManager();
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
           /* startButton = true;
            myStartService.setEnabled(true);
            myStartService.setTextColor(Color.parseColor("#FF6A00"));*/
           /* Toast.makeText(view.getContext(), "Alarm is set to  " + time/1000/60/60 + " hours and "
                    + (time/1000/60)%60 + " minutes" , Toast.LENGTH_SHORT).show();*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
