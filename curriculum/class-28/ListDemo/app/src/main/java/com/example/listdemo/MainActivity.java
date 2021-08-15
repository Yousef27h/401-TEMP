package com.example.listdemo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    String[] shoppingItems = {
            "Apples",
            "Oranges",
            "Mangoes",
            "Rice",
            "Strawberries",
            "Carrots",
            "Bread",
            "Yogurt",
            "Ham",
            "Beef",
            "Chicken",
            "Jam",
            "Nuts",
            "Water",
            "Juice",
            "Cheese",
            "Peanut Butter",
            "Chocolate",
            "Melon",
            "Pumpkin",
            "Chips",
            "Humus"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                shoppingItems
                );
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings was clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_food) {
            Intent secondListIntent = new Intent(this, SecondListActivity.class);
            startActivity(secondListIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}