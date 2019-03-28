package com.example.filmsserialsapp;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button addButton;
    Button deleteButton;
    Button sawButton;
    Button saveButton;
    Button loadButton;
    ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    static final String LOG_TAG = "myLogs";

    static final String FILENAME = "file";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getId();
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            openFileOutput(FILENAME, MODE_PRIVATE)));
                    bw.write("Содержимое файла");
                    bw.close();
                    Log.d(LOG_TAG, "Файл записан");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
         loadButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 try {
                     BufferedReader br = new BufferedReader(new InputStreamReader(
                             openFileInput(FILENAME)));
                     String str = "";
                     while ((str = br.readLine()) != null) {
                         Log.d(LOG_TAG, str);
                     }
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
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
    public void getId(){
        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.button_add);
        deleteButton = findViewById(R.id.button_toast_delete);
        sawButton = findViewById(R.id.button_toast_saw);
        listView = findViewById(R.id.list_view);
        saveButton = findViewById(R.id.save_list);
        loadButton = findViewById(R.id.load_list);
    }
}
