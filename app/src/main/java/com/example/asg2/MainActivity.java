package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileRead("items");
    }

    @Override
    public void onClick(View v) {

    }

    // Am I even doing this right?
    public ArrayList<String> fileRead(String filePath) {
        ArrayList<String> fileArr = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(getResources().getIdentifier(filePath, "raw", getPackageName()));
            Scanner reader = new Scanner(inputStream);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                fileArr.add(line);
            }
            reader.close();
            inputStream.close();
        } catch(IOException ignore) {
            Log.e("MainActivity - fileRead","File " + filePath + " not found. Error in fileRead() method.");
        }
        return fileArr;
    }
}