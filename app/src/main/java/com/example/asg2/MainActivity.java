package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
    ArrayAdapter<Item> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newList = findViewById(R.id.mainList);
        // on MainActivity startup, read items.txt into ArrayList<Item> and call generateListView()
        itemList = readItems();
        generateListView(itemList);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);

        newList.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
arrayAdapter.getFilter().filter(newText);

                return false;
            }
        });

    return super.onCreateOptionsMenu(menu);
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