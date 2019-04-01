package com.example.filmsserialsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
    Button loadButton;
    ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    public static final String FILMS_SERIALS = "films_serials";
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getId();

        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty((editText.getText().toString().trim()))) {
                    listItems.add(editText.getText().toString());
                    editText.getText().clear();
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Null!", Toast.LENGTH_LONG).show();
                }
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

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFilms();
                v.setClickable(false);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView addStrikeThrough = (TextView) view;
                if ((addStrikeThrough.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) == 0) {
                    addStrikeThrough.setPaintFlags(addStrikeThrough.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                } else {
                    addStrikeThrough.setPaintFlags(0);

                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView addStrikeThrough = (TextView) view;
                if ((addStrikeThrough.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) == Paint.STRIKE_THRU_TEXT_FLAG) {
                    addStrikeThrough.setPaintFlags(0);
                    adapter.remove(adapter.getItem(position));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    public void writeFilms() {
        SharedPreferences prefs = context.getSharedPreferences(FILMS_SERIALS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        for (int i = 0; i < adapter.getCount(); ++i) {
            editor.putString(String.valueOf(i), adapter.getItem(i));
        }
        editor.apply();
    }

    public void readFilms() {
        SharedPreferences prefs = context.getSharedPreferences(FILMS_SERIALS, MODE_PRIVATE);
        for (int i = 0; ; ++i) {
            final String str = prefs.getString(String.valueOf(Integer.valueOf(i)), "");
            if (!str.equals("")) {
                adapter.add(str);
            } else {
                break;
            }
        }
    }

    public void getId() {
        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.button_add);
        deleteButton = findViewById(R.id.button_toast_delete);
        sawButton = findViewById(R.id.button_toast_saw);
        listView = findViewById(R.id.list_view);
        loadButton = findViewById(R.id.load_list);
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeFilms();
    }
}
