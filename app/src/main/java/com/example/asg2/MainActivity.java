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
     *  You can call this method as such:
     *  ArrayList<Item> testName = readItems();
     *
     * @return ArrayList<String>: Returns an ArrayList of String;
     *      Will be converted to ArrayList<Items> once items has been uploaded.
     */
    public ArrayList<Item> readItems() {
        ArrayList<Item> fileArr = new ArrayList<>();
        String filePath = "items";
        String[] split;
        Item item;
        int id;
        String name;
        int quantity;
        double cost;
        int suppId;

        try {
            InputStream inputStream = getResources().openRawResource(getResources().getIdentifier(filePath, "raw", getPackageName()));
            Scanner reader = new Scanner(inputStream);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                split = line.split(";");
                // Fill in all the variables with the parsed values form the 'split' Array
                id = Integer.parseInt(split[0]);
                name = split[1];
                quantity = Integer.parseInt(split[2]);
                cost = Double.parseDouble(split[3]);
                suppId = Integer.parseInt(split[4]);

                item = new Item(id, name, quantity, cost,suppId);
                fileArr.add(item);
            }
            reader.close();
            inputStream.close();
        } catch(IOException ignore) {
            Log.e("MainActivity - fileRead","File " + filePath + " not found. Error in fileRead() method.");
        }
        return fileArr;
    }
}