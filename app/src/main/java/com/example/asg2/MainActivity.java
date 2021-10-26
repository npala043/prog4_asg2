package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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
        for(int i = 0; i <= 15; i++) {
            Item item = new Item(i, "Item " + i, 0, 0, 0);
            itemList.add(item);
        }

        generateListView(itemList);

    }

    @Override
    public void onClick(View v) {

    }

    private void generateListView(ArrayList<Item> itemList) {
        ListView mainList = (ListView) findViewById(R.id.mainList);
        ItemsAdapter adapter = new ItemsAdapter(this, itemList);
        mainList.setAdapter(adapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, ItemPage.class);
                startActivity(intent);
            }
        });
    }

}