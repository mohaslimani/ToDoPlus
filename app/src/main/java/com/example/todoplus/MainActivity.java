package com.example.todoplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity{

    RecyclerView recycle;
    FloatingActionButton floatingActionButton;
    RecycleViewAdapter recycleViewAdapter;
    EditText input_title;
    EditText input_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle = findViewById(R.id.recycle);
        floatingActionButton = findViewById(R.id.add_button);

        floatingActionButton.setOnClickListener(this::showDialog);

        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycleViewAdapter = new RecycleViewAdapter(this);
        recycle.setAdapter(recycleViewAdapter);
    }

    public void showDialog(View V)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View linearLayout = View.inflate(this, R.layout.input_text, null);

        input_title = linearLayout.findViewById(R.id.input_title);
        input_con = linearLayout.findViewById(R.id.input_con);

        builder.setView(linearLayout);

        builder.setPositiveButton("Ok", this::onAddNote);
        builder.setNegativeButton("Cancel",null);

        builder.show();
    }

    public void onAddNote(DialogInterface dialog, int which)
    {
        String title = input_title.getText().toString();
        String content = input_con.getText().toString();
        recycleViewAdapter.addNote(title, content);
    }
}