package com.hieuminh.diaryv1;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.hieuminh.diaryv1.model.EventModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private Context context;
    private ArrayList<EventModel> events;

    public EventAdapter(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventHolder(LayoutInflater.from(context).inflate(R.layout.item_event,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
       try {
           holder.titleEvent.setText(events.get(position).getTitle());
           holder.descEvent.setText(events.get(position).getDescription());
           holder.dateEvent.setText(events.get(position).getHour() + ":" +events.get(position).getMin());
           holder.tvHistory.setText(setTime(events.get(position)));
       } catch (Exception e) {
//           holder.dateEvent.setText("Loi");
       }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView titleEvent, descEvent, dateEvent, tvHistory;
        public EventHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            titleEvent = view.findViewById(R.id.title_event);
            descEvent = view.findViewById(R.id.desc_event);
            dateEvent = view.findViewById(R.id.date_event);
            tvHistory = view.findViewById(R.id.tv_history);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String setTime(EventModel model) {
        if(model.getMonth() != LocalDate.now().getMonthValue()) {
            return LocalDate.now().getMonthValue() - model.getMonth() + " month ago";
        } else if(model.getDay() != LocalDate.now().getDayOfMonth()) {
            return LocalDate.now().getDayOfMonth() - model.getDay() + " day ago";
        } else if(model.getHour() != LocalTime.now().getHour()) {
            return LocalTime.now().getHour() - model.getHour() + " hour ago";
        } else if(model.getMin() != LocalTime.now().getMinute()) {
            return LocalTime.now().getMinute() - model.getMin() + " minute ago";
        } else if(model.getSecond() != LocalTime.now().getSecond()) {
            return LocalTime.now().getSecond() - model.getSecond() + " second ago";
        }
        return null;
    }
}
