package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateItem extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
    }

    @Override
    public void onClick(View v) {

    }

    public void writeItems(ArrayList<Item> fileArr) {
        String filePath = "items";
        String item = "";
        int size = fileArr.size();
        int index = 0;
        try {
            InputStream inputStream = getResources().openRawResource(getResources().getIdentifier(filePath, "raw", getPackageName()));
            Scanner scanner = new Scanner(inputStream);
            for (String line : fileArr) {
                index++;
                if (size > index) {
                    //Write a line + newline ("\n")
                } else {
                    //Write a line (w/o newline)
                }
            }
            scanner.close();
            inputStream.close();
        } catch(IOException ignore) {
            Log.e("CreateItem - fileRead","File " + filePath + " not found. Error in fileRead() method.");
        }
    }
}