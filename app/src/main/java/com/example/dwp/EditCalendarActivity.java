package com.example.dwp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class EditCalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int i, int i1, int i2) {
                String date = (i1+1) + "/" + i2 + "/" + i;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy " + date);

                Intent intent = new Intent(EditCalendarActivity.this,TimeActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}