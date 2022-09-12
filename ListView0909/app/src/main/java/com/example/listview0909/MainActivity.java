package com.example.listview0909;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMonHoc;
    Button btn_them;
    Button btn_sua;
    Button btn_clear;
    EditText monhoc_edit;
    ArrayList<String> arrayCourse;
    int vitri = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMonHoc = (ListView) findViewById(R.id.monhoc);
        btn_them = (Button) findViewById(R.id.add_btn);
        monhoc_edit = (EditText) findViewById(R.id.editText);
        btn_sua= (Button) findViewById(R.id.edit_btn);
        btn_clear = (Button) findViewById(R.id.clear_btn);
        arrayCourse = new ArrayList<>();


        arrayCourse.add("PHP");
        arrayCourse.add("JS");
        arrayCourse.add("HTML");
        arrayCourse.add("JAVA");
        arrayCourse.add("Python");
        arrayCourse.add("SQL");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayCourse);

        lvMonHoc.setAdapter(adapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monhoc = monhoc_edit.getText().toString();
                arrayCourse.add(monhoc);
                adapter.notifyDataSetChanged();
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                monhoc_edit.setText(arrayCourse.get(i));
                vitri = i;
            }
        });
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_dialer)
                        .setTitle("Màn hình chi tiết: ")
                        .setMessage("Nội dung: " + arrayCourse.get(i))
                        .show();
                return false;
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.set(vitri, monhoc_edit.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ? ")
                        .setMessage("Do you want to delete this " + arrayCourse.get(vitri))
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrayCourse.remove(vitri);
                                adapter.notifyDataSetInvalidated();
                                monhoc_edit.setText("");
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });
    }
}

