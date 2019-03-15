package com.example.filmsserialsapp;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button addButton;
    Button deleteButton;
    Button sawButton;
    ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.button_add);
        deleteButton=findViewById(R.id.button_toast_delete);
        sawButton = findViewById(R.id.button_toast_saw);
        listView = findViewById(R.id.list_view);
        listItems = new ArrayList<>();
        listItems.add("Supernatural ");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItems.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Long Click", Toast.LENGTH_LONG).show();
            }
        });
        sawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView addstrikethrough = (TextView) view;
                if ((addstrikethrough.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) == 0) {
                    addstrikethrough.setPaintFlags(addstrikethrough.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    addstrikethrough.setPaintFlags(0);

                }
            }
        });

    }


    /*private static int x = 0;

    @Override
    protected void onPause(){
        super.onPause();
        submitPrefs();
    }
    @Override
    protected void onResume(){
        super.onResume();
        getStoredPrefs();
    }
    private void submitPrefs(){
        SharedPreferences preferences = getBaseContext().getSharedPreferences("MyPrefsX", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("InputX","My Iteration"+x);
        editor.commit();
        x++;
    }
    private void getStoredPrefs(){
        SharedPreferences preferences = getBaseContext().getSharedPreferences("MyPrefsX", MODE_PRIVATE);
        String s = preferences.getString("InputX", "Not set yet");
    }*/
}

