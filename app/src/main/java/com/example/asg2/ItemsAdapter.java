// Originally taken from StackOverflow, refactored to fit our needs

package com.example.asg2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<Item> {

    public ItemsAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lvi_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.tvQuantity);
        // Populate the data into the template view using the data object
        tvName.setText(item.getName());
        tvQuantity.setText("Quantity: " + item.getQuantity());
        // Return the completed view to render on screen
        return convertView;
    }
}
