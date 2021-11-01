package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateItem extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
    }

    public void returnValues(View v){

        EditText itemID = (EditText) findViewById(R.id.itemID);
        EditText itemName = (EditText) findViewById(R.id.itemName);
        EditText itemQuantity = (EditText) findViewById(R.id.itemQuantity);
        EditText itemCost = (EditText) findViewById(R.id.itemCost);
        EditText supplierID = (EditText) findViewById(R.id.supplierID);

        int ID = Integer.valueOf(itemID.getText().toString());
        String name = itemName.getText().toString();
        int quantity = Integer.valueOf(itemQuantity.getText().toString());
        int cost = Integer.valueOf(itemCost.getText().toString());
        int supID = Integer.valueOf(supplierID.getText().toString());

        Bundle b = new Bundle();
        b.putInt("id", ID);
        b.putString("name", name);
        b.putInt("quantity",quantity);
        b.putDouble("cost", cost);
        b.putInt("supId", supID);

        //resultIntent sends the result back to MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtras(b);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }
}