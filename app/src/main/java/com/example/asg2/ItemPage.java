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

        TextView tv = (TextView) findViewById(R.id.itemName);
        Item item = (Item) getIntent().getSerializableExtra("item");
        tv.setText(item.getName());
    }

    @Override
    public void onClick(View v) {

    }
}