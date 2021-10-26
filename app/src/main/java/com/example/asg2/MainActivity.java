package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

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

        // on MainActivity startup, read items.txt into ArrayList<Item> and call generateListView()
        ArrayList<Item> itemList = new ArrayList<>(); // replace once readFile() is complete

        // dummy arraylist fill
        for(int i = 0; i < 15; i++) {
            Item item = new Item(i, "Item " + i, 0, 0, 0);
            itemList.add(item);
        }
        generateListView(itemList);
    }

    @Override
    public void onClick(View v) {

    }

    private void generateListView(ArrayList<Item> itemList) {
        ListView mainList = findViewById(R.id.mainList);

        // Facilitates custom creation of ListView from ArrayList
        ItemsAdapter adapter = new ItemsAdapter(this, itemList);
        mainList.setAdapter(adapter);

        // Click listener for each item in ListView
        // Original code from StackOverflow, refactored to match this project and converted to lambda expression
        mainList.setOnItemClickListener((parent, view, position, id) -> {
            Item item = adapter.getItem(position);

            Bundle b = new Bundle();
            b.putSerializable("item", item);

            Intent intent = new Intent(MainActivity.this, ItemPage.class);
            intent.putExtras(b);
            startActivity(intent);
        });
    }

    /**
     *  Can call like so: fileRead("items");
     * @param filePath: This is the file path without the '.txt' extension.
     *
     * @return ArrayList<String>: Returns an ArrayList of String;
     *      Will be converted to ArrayList<Items> once items has been uploaded.
     */
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