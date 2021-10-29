package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        Item item = (Item) getIntent().getSerializableExtra("item");

        // Set item name
        TextView tvName = (TextView) findViewById(R.id.itemName);
        tvName.setText(item.getName());

        // Set item quantity
        TextView tvQuant = findViewById(R.id.itemQuant);
        tvQuant.setText(Integer.toString(item.getQuantity()));

        // Set item cost
        TextView tvCost = findViewById(R.id.itemCost);
        tvCost.setText(Double.toString(item.getCost()));

        // Set item supplier id
        TextView tvSuppId = findViewById(R.id.itemSuppId);
        tvSuppId.setText(Integer.toString(item.getSuppId()));
    }

    @Override
    public void onClick(View v) {

    }
}