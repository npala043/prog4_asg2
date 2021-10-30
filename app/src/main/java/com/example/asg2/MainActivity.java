package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Item> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on MainActivity startup, read items.txt into ArrayList<Item> and call generateListView()
        this.itemsList = readItems();
        generateListView(this.itemsList);
    }

    /**
     * This method is called when the New Item button is clicked and takes users to a second page
     *
     * @param v
     */
    public void newItemClick(View v){
        Intent intent = new Intent(MainActivity.this, CreateItem.class);
        startActivityForResult(intent, 0);
        //Asks to receive bundle when activity finishes
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        int id = b.getInt("id");
        String name = b.getString("name");
        int quantity = b.getInt("quantity");
        double cost = b.getDouble("cost");
        int supID = b.getInt("supId");

        Item newItem = new Item(id, name, quantity, cost, supID);
        //write newItem to the file
        writeItem(newItem);
        generateListView(this.itemsList);
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
     * Instead of writing to the items.txt file (because res is a read-only folder)
     * we will instead only create new items within the current instance. On reload
     * newly created items will not persist
     */
    public void writeItem(Item item){
        this.itemsList.add(item);

//        String itemLine = item.getId()+";"+item.getName()+";"+item.getQuantity()+";"+item.getCost()+";"+item.getSuppId();
//        FileOutputStream fOut = openFileOutput(getResources().openRawResource(getResources().getIdentifier("items", "raw", getPackageName())), true);
//        OutputStreamWriter osw = new OutputStreamWriter(fOut);
//        try {
//            osw.write(itemLine);
//            osw.flush();
//            osw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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