// Originally taken from StackOverflow, refactored to fit our needs

package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);



    }

    @Override
    public void returnValues(View v){

        EditText itemID = (EditText) findViewByID(R.id.itemID);
        EditText itemName = (EditText) findViewByID(R.id.itemName);
        EditText itemQuantity = (EditText) findViewByID(R.id.itemQuantity);
        EditText itemCost = (EditText) findViewByID(R.id.itemCost);
        EditText supplierID = (EditText) findViewByID(R.id.supplierID);

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
