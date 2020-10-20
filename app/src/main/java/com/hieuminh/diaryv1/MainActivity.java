package com.hieuminh.diaryv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hieuminh.diaryv1.model.EventModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView titlePage, subtitilePage, endPage;
    private RecyclerView rvEvent;
    private DatabaseReference mData;
    private EventAdapter eventAdapter;
    private ArrayList<EventModel> events;
    private ImageView ivAddNewDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = FirebaseDatabase.getInstance().getReference();



        connectView();
        events = new ArrayList<>();
        Typeface Mlight = Typeface.createFromAsset(getAssets(),"fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MM.ttf");

        subtitilePage.setTypeface(MMedium);
        subtitilePage.setTypeface(Mlight);
        endPage.setTypeface(Mlight);


//        mData.child("diary").push().setValue(new EventModel("BT","bài tập",(new Timestamp(System.currentTimeMillis()))+""));

        mData.child("diary").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    EventModel model =   ds.getValue(EventModel.class);
                    events.add(model);
                }
                rvEvent.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                eventAdapter = new EventAdapter(MainActivity.this, events);
                rvEvent.setAdapter(eventAdapter);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void connectView() {
        titlePage = findViewById(R.id.title_page);
        subtitilePage = findViewById(R.id.subtitle_page);
        endPage = findViewById(R.id.end_page);
        rvEvent = findViewById(R.id.rv_event);
        ivAddNewDiary = findViewById(R.id.iv_add_new_diary);
        ivAddNewDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

    }

    //        tv = findViewById(R.id.tv);
//        tv1 = findViewById(R.id.tv1);
//        mData = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("message");
//        // trường hợp 1
//        mData.child("title").setValue("Đặng Minh Hiếu");
//
//        // trường hợp 2
//        EventModel model = new EventModel("sự kiện 2", "Ngày mưa");
////        mData.child("event").setValue(model);
//
//        // trường hợp 3
//        Map<String, String> myMap = new HashMap<>();
//        myMap.put("title", "event2");
//        myMap.put("description", "Ngày rất mưa");
////        mData.child("event1").setValue(myMap);
//
//        // trường hợp 4
//        EventModel model1 = new EventModel("sự kiện 3", "Ngày mưa1");
////        mData.child("sukien").push().setValue(model1, new DatabaseReference.CompletionListener() {
////            @Override
////            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//////                if (error == null) {
//////                    tv.setText("Lưu dữ liệu vào thành công!");
//////                } else {
//////                    tv.setText("Lưu dữ liệu vào thất bại!");
//////                }
////            }
////        });
//
////        mData.child("event").setValue(new EventModel("sự kiện thay đổi", "miêu tả bất lực"));
//        mData.child("event").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                EventModel newModel = snapshot.getValue(EventModel.class);
////                tv.setText(newModel.getTitle());
////
////                tv1.setText(newModel.getDescription());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        mData.child("sukien").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                EventModel model = snapshot.getValue(EventModel.class);
//                count++;
//                tv.setText(model.getTitle() + " " + count );
//                tv1.setText(model.getDescription());
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
}