package com.alexjolong.alexhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import java.text.*;
import java.util.TimeZone;

import android.widget.EditText;
import android.widget.TextView;

/*
 * Joda time from https://github.com/dlew/joda-time-android
 * Should handle leap years and different timezone.
 */
import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;


public class MainActivity extends AppCompatActivity {

    private DateTimeZone JodaTimeZoneDefault;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JodaTimeAndroid.init(this);
        try {
            JodaTimeZoneDefault = DateTimeZone.forTimeZone(TimeZone.getDefault());
        }
        catch (IllegalArgumentException ille) {
            Log.e("handler", "Android timezone ID wasn't readable by Joda Time. Using default time zone.");
            JodaTimeZoneDefault = DateTimeZone.getDefault();
        }


        long daysLeftLong = calculateDaysLeft();
        TextView daysLeft = (TextView) findViewById(R.id.textView);
        daysLeft.setText(daysLeftLong + " Days Left!");
    }

    public long calculateDaysLeft(DateTime death) {
        // death time is assumed to be CST
        DateTime now = DateTime.now();
        Duration timeDiff = new Duration(now, death);
        return timeDiff.getStandardDays();
    }

    public long calculateDaysLeft() {
        DateTime deathDate = new DateTime(2071, 6, 24, 0, 0, JodaTimeZoneDefault);
        return calculateDaysLeft(deathDate);
    }

    /*
    public void submitFeatureIdea(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
    }
    */
}
