package com.hieuminh.diaryv1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hieuminh.diaryv1.model.EventModel;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddEventActivity extends AppCompatActivity {
    private static DatabaseReference mData;
    private static EditText addTitleEvent, addDescEvent;
    private static Button btSaveNewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        connectView();
    }

    @SuppressLint("WrongViewCast")
    private void connectView() {
        addTitleEvent = findViewById(R.id.add_title_event);
        addDescEvent = findViewById(R.id.add_desc_event);
        btSaveNewEvent = findViewById(R.id.bt_save_new_event);
        btSaveNewEvent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                mData = FirebaseDatabase.getInstance().getReference();
                String title = addTitleEvent.getText().toString();
                if (title.equals("")) {
                    Toast.makeText(AddEventActivity.this, "Please enter title", Toast.LENGTH_SHORT).show();
                    return;
                }
                String desc = addDescEvent.getText().toString();
                if (desc.equals("")) {
                    Toast.makeText(AddEventActivity.this, "Please enter description", Toast.LENGTH_SHORT).show();
                    return;
                }
                EventModel model = new EventModel(title, desc);
                mData.child("diary").push().setValue(model);

                Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}