package com.mycompany.myservice;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.os.Environment;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PickSongActivity extends ListActivity {

    private String root;
    private List<String> dirList;
    private List<String> songList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_song);


        dirList = new ArrayList<String>();
        songList = new ArrayList<String>();
        root = Environment.getExternalStorageDirectory().getPath();
        traverse(new File(root));
        ArrayAdapter<String> newList =
                new ArrayAdapter<String>(this, R.layout.row, songList);
        setListAdapter(newList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        MainActivity.path = dirList.get(position);
        MainActivity.song = songList.get(position);
        SharedPreferences.Editor editor = MainActivity.mySongPref.edit();
        editor.putString(MainActivity.MY_SONG, MainActivity.song);
        editor.putString(MainActivity.PREF_PATH, dirList.get(position));
        editor.putBoolean(MainActivity.PICK_YET, true);

        editor.commit();

        MainActivity.alreadyPickSong = true;
        startActivity(new Intent(v.getContext(), MainActivity.class));

    }

    public void traverse(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; ++i) {
                File file = files[i];
                if (file.isDirectory()) {
                    traverse(file);
                } else {
                    // do something here with the file
                    String name = file.getName();
                    if (name.endsWith(".mp3") || name.endsWith(".wave")) {
                        songList.add(name);
                        dirList.add(file.getPath());
                    }
                }
            }
        }
    }

}