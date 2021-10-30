package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Item> itemList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // on MainActivity startup, read items.txt into ArrayList<Item> and call generateListView()
        itemList = readItems();
        generateListView(itemList);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * This method will rewrite the ListView with the supplied ArrayList<Item>. When a ListView
     * item is clicked, it will be passed along to its corresponding ItemPage through an onClick listener
     *
     * @param itemList: ArrayList of Item objects which will populate the ListView
     */
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
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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
            scanner.close();
            inputStream.close();
        } catch(IOException ignore) {
            Log.e("MainActivity - fileRead","File " + filePath + " not found. Error in fileRead() method.");
        }
        return fileArr;
    }
}