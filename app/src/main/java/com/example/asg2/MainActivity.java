package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

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

}