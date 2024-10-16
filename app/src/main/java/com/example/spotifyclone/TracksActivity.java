package com.example.spotifyclone;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class TracksActivity extends AppCompatActivity {

    ListView lV;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        lV = findViewById(R.id.lsViewTrend);

        runtimePermission();

    }

    public void runtimePermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                displaySongs();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    //Original Code
//    public ArrayList<File> findSong (File file)
//    {
//        ArrayList<File> arrayList = new ArrayList<>();
//
//        File[] files = file.listFiles();
//
//        for (File singlefile: files)
//        {
//            if (singlefile.isDirectory() && !singlefile.isHidden())
//            {
//                arrayList.addAll(findSong(singlefile));
//            }
//            else
//            {
//                if(singlefile.getName().endsWith(".mp3") || singlefile.getName().endsWith(".wav"))
//                {
//                    arrayList.add(singlefile);
//                }
//            }
//        }
//        return arrayList;
//    }

    //Gpt Code
    public ArrayList<File> findSong(File file) {
        ArrayList<File> arrayList = new ArrayList<>();

        if (file == null) {
            // Handle null file (e.g., external storage not available)
            return arrayList;
        }

        File[] files = file.listFiles();

        if (files == null) {
            // Handle empty directory or access denied
            return arrayList;
        }

        for (File singlefile : files) {
            if (singlefile.isDirectory() && !singlefile.isHidden()) {
                arrayList.addAll(findSong(singlefile));
            } else {
                if (singlefile.getName().endsWith(".mp3") || singlefile.getName().endsWith(".wav")) {
                    arrayList.add(singlefile);
                }
            }
        }
        return arrayList;
    }


    void displaySongs()
    {
        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());

        items  = new String[mySongs.size()];
        for (int i = 0; i<mySongs.size(); i++)
        {
            items[i] = mySongs.get(i).getName().toString().replace("mp3","").replace("wav","");
        }
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//        lV.setAdapter(myAdapter);

        customAdapter cusAdapter = new customAdapter();
        lV.setAdapter(cusAdapter);

        lV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String songName = (String) lV.getItemAtPosition(i);
                startActivity(new Intent(getApplicationContext(), MusicActivity.class)
                        .putExtra("songs", mySongs)
                        .putExtra("song name", songName)
                        .putExtra("pos",i));
            }
        });

    }

    class customAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = getLayoutInflater().inflate(R.layout.list_item,null);
            TextView txtSong = myView.findViewById(R.id.txtsongname);
            txtSong.setSelected(true);
            txtSong.setText(items[position]);

            return myView;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(TracksActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }


}