package com.example.timezoneconverter1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {
//    EditText editText;
    TextView textView,textView1,from,to,timeZones,tv_timer1;
    Button button;
    public static int hrs,mins,diff_hrs,diff_mins,x,u,hrS,minS,diff,diff1;
    int tHour,tMin;
    String h;
    public char c,d;
    Spinner hour,minute,second, fromCountry, toCountry;
//    int[] hours = new int[]{}, minutes = new int[]{}, seconds = new int[]{};
    void check(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        textView1 = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        timeZones = findViewById(R.id.timeZones);
        tv_timer1=findViewById(R.id.tv_timer1);
        button = findViewById(R.id.Convert);
//        hour = findViewById(R.id.hours);
//        minute = findViewById(R.id.minutes);
//        second = findViewById(R.id.seconds);
        toCountry = findViewById(R.id.ToCountry);
        fromCountry = findViewById(R.id.FromCountry);
//        for(int i=0; i<=24; i++){
//            hours[i] = i;
//        }
//        for(int i=0; i<=60; i++){
//            minutes[i] = i;
//        }
//        for(int i=0; i<=60; i++){
//            seconds[i] = i;
//        }
//        ArrayList<Integer> hours = new ArrayList<>();
//        for(int i=0; i<24; i++){
//            hours.add(i);
//        }
//        ArrayList<Integer> minutes = new ArrayList<>();
//        for(int i=0; i<60; i++){
//            minutes.add(i);
//        }
//        ArrayList<Integer> seconds = new ArrayList<>();
//        for(int i=0; i<60; i++){
//            seconds.add(i);
//        }
        ArrayList<String> fromCountries = new ArrayList<>();
        fromCountries.add("India");
        fromCountries.add("Chicago");
        fromCountries.add("Melbourne");
        fromCountries.add("London");
        fromCountries.add("Johannesburg");
//        fromCountries.add("Illinois");

        ArrayList<String> toCountries = new ArrayList<>();
        toCountries.add("India");
        toCountries.add("Chicago");
        toCountries.add("Melbourne");
        toCountries.add("London");
        toCountries.add("Johannesburg");
//        toCountries.add("Illinois");
        tv_timer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHour = hourOfDay;
                                tMin = minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,tHour,tMin);
                                tv_timer1.setText(DateFormat.format("hh:mm aa", calendar));
                                minS=tMin;
                                hrS=tHour;
//                                mins=minS;
//                                hrs=hrS;
                                u = calendar.get(Calendar.AM_PM);
                            }
                        },12,0,false
                );
                timePickerDialog.updateTime(tHour,tMin);
                timePickerDialog.show();
            }
        });
//        hour.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,hours));
//        hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String Hrs = parent.getItemAtPosition(position).toString();
//                hrs = Integer.parseInt(Hrs);
//                hrS = hrs;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        minute.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, minutes));
//        minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String Mins = parent.getItemAtPosition(position).toString();
//                mins = Integer.parseInt(Mins);
//                minS = mins;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        second.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, seconds));
//        second.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String Secs = parent.getItemAtPosition(position).toString();
//                secs = Integer.parseInt(Secs);
//                secS=secs;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        toCountry.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,toCountries));
        toCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                if(text.equals("India")){
                    d=(char) 0;
                }
                if(text.equals("Chicago")){
                    d=(char) 1;
                }
                if(text.equals("Melbourne")){
                    d=(char) 2;
                }
                if(text.equals("London")){
                    d=(char) 3;
                }
                if(text.equals("Johannesburg")){
                    d=(char) 4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fromCountry.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,fromCountries));
        fromCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                if(text.equals("India")){
                    c=(char) 0;
                }
                if(text.equals("Chicago")){
                    c=(char) 1;
                }
                if(text.equals("Melbourne")){
                    c=(char) 2;
                }
                if(text.equals("London")){
                    c=(char) 3;
                }
                if(text.equals("Johannesburg")){
                    c=(char) 4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(v -> {
//            String time = editText.getText().toString();
            diff_hrs = 0;
            diff_mins = 0;
//            secs = Integer.parseInt(Secs);
//            hrs = Integer.parseInt(Hrs);
//            mins = Integer.parseInt(Mins);
            mins = minS;
            hrs = hrS;
            hrS=hrs;
            minS=mins;
            int U =u;
            int z=0;
            switch(c) {
                case 0:
                    diff = 5;
                    diff1=30;
                    if(u==0) {
                        if(hrS<diff) {
                            hrs = hrs + 12 -diff;
                            u = 1;
                            if(minS<diff1){
                                mins = mins + 60 - diff1;
                                hrs = hrs - 1;
                            }
                            else{
                                mins = mins - 30;
                            }
                        }
                        else if(hrS>diff){
                            hrs = hrs - diff;
                            if(minS<diff1){
                                mins = mins + 60 - diff1;
                                hrs = hrs - 1;
                            }
                            else{
                                mins = mins - 30;
                            }
                        }
                        else {
                            if(minS<diff1){
                                hrs = hrs +12 -diff;
                                mins = mins + 60 - diff1;
                                hrs = hrs - 1;
                                u=1;
                            }
                            else {
                                hrs = 0;
                                mins = mins - diff1;
                            }
                        }
                    }
                    else if(u==1){
                        if((hrS-12)<diff) {
                            hrs = hrs -diff;
                            u = 0;
                            if(minS<diff1){
                                mins = mins + 60 - diff1;
                                hrs = hrs - 1;
                            }
                            else{
                                mins = mins - 30;
                            }
                        }
                        else if((hrS-12)>diff){
                            hrs = hrs -12 - diff;
                            if(minS<diff1){
                                mins = mins + 60 - diff1;
                                hrs = hrs - 1;
                            }
                            else{
                                mins = mins - 30;
                            }
                        }
                        else {
                            hrs = hrs -12;
                            if(minS<diff1){
                                hrs = hrs +12 -diff;
                                mins = mins + 60 - diff1;
                                hrs = hrs - 1;
                                u=0;
                            }
                            else if(minS>diff1){
                                hrs = 0;
                                mins = mins - diff1;
                            }
                            else{
                                hrs = 12;
                                mins = 0;
                            }
                        }
                    }
                    hrS=hrs;
                    minS=mins;
//                    hrs = hrs - diff;
//                    if (mins >= 30 && mins <= 60) {
//                        mins = mins - 30;
//                    }
//                    else if (mins >= 0 && mins < 30) {
//                        hrs = hrs - 1;
//                        mins = mins + 30;
//                    }
                    break;
                case 1:
                    diff = -6;
                    diff1 = 0;
                    diff = Math.abs(diff);
//                    int temp = hrS;
                    if(u==0) {
                        if(hrS<12-diff) {
                            hrs = hrs + diff;
//                            u=1;
                        }
                        else if(hrS>12-diff){
                            hrs =  hrs + diff -12;
                            u=1;
                        }
                        else {
                            hrs = 0;
                            u=1;
                        }
                    }
                    else if(u==1){
                        hrS = hrS -12;
                        hrs = hrS;
                        if(hrS<12-diff) {
                            hrs = hrs + diff;
//                            u=0;
                        }
                        else if(hrS>12-diff) {
                            hrs = hrs - 12 + diff;
                            u=0;
                        }
                        else {
                            hrs = 0;
                            u=0;
                        }
//                        hrS=temp;
                    }
                    hrS=hrs;
                    break;
                case 2:
                    diff = 11;
                    if(u==0) {
                        if(hrS<diff) {
                            hrs = hrs + 12 - diff;
                            u = 1;
                        }
                        else{
                            hrs = hrs - diff;
                        }
                    }
                    else if(u==1){
                        if((hrS-12)<diff) {
                            hrs = hrs - diff;
                            u=0;
                        }
                        else if((hrS-12)>diff){
                            hrs = hrs - 12 - diff;
                        }
                        else{
                            hrs=12;
                        }
                    }
                    hrS=hrs;
                    break;
                case 3:
                    diff=0;
                    if(u==0) {
                        if(hrS<diff) {
                            hrs = hrs + 12 - diff;
                            u = 1;
                        }
                        else{
                            hrs = hrs - diff;
                        }
                    }
                    else if(u==1){
                        if((hrS-12)<diff) {
                            hrs = hrs - diff;
                            u=0;
                        }
                        else if((hrS-12)>diff){
                            hrs = hrs - 12 - diff;
                        }
                        else {
                            hrs=0;
                        }
                    }
                    hrS=hrs;
//                    if(hrs>12)
//                        hrs = hrs -12;
                    break;
                case 4:
                    diff = 2;
                    if(u==0) {
                        if(hrS<diff) {
                            hrs = hrs + 12 - diff;
                            u = 1;
                        }
                        else if(hrS>diff){
                            hrs = hrs - diff;
                        }
                        else {
                            hrs = 0;
                        }
                    }
                    else if(u==1){
                        if((hrS-12)<diff) {
                            hrs = hrs - diff;
                            u=0;
                        }
                        else if((hrS-12)>diff){
                            hrs = hrs - 12 - diff;
                        }
                        else {
                            hrs = 12;
                        }
                    }
                    hrS=hrs;
                    break;
            }
            if(u==0)
                h = "am";
            else if(u==1)
                h = "pm";
            switch(d){
                case 0:
                    diff = -5;
                    diff1 = -30;
//                    if(mins>=60) {
//                        mins = mins - 60;
//                        hrs = hrs + 1;
//                    }
//                    if(mins<0) {
//                        mins += 60;
//                        hrs = hrs - 1;
//                    }
//                    if(hrs<0){
//                        hrs=hrs+24;
//                    }
//                    else if(hrs>24){
//                        hrs = hrs -24;
//                    }
                    diff = Math.abs(diff);
                    diff1= Math.abs(diff1);
                    if(u==0) {
                        if(hrS<11-diff) {
                            hrs = hrs + diff;
                            if(minS<diff1){
                                mins = mins + diff1;
                            }
                            else if(minS>diff1){
                                mins = mins - diff1;
                                hrs = hrs + 1;
                            }
                            else{
                                mins = mins - diff1;
                                hrs = hrs +1;
                            }
                        }
                        else if(hrS>11-diff){
                            if(hrs==7)
                                hrs =12;
                            else
                                hrs = hrs + diff - 12;
                            u=1;
                            if(minS<60-diff1){
                                mins = mins + diff1;
//                                hrs=hrs + diff ;
                            }
                            else {
                                mins = mins - 30;
                                hrs = hrs + 1;
                            }
                        }
                        else {
                            if(minS<60-diff1){
                                hrs = hrs + diff;
                                mins = mins + diff1;
//                                u=1;
                            }
                            else if(minS>60-diff1){
                                mins = mins - diff1;
                                hrs= 12;
                                u=1;
                            }
                            else{
                                hrs = 12;
                                mins = 0;
                                u=1;
                            }
                        }
                    }
                    else if(u==1){
                        if(hrS<11-diff) {
//                            if(hrs==12)
//                                hrs =hrs+diff;
//                            else
//                                hrs = diff;

                            hrs = hrs + diff;
                            if(minS<diff1){
                                mins = mins + diff1;
                            }
                            else if(minS>diff1){
                                mins = mins - diff1;
                                hrs = hrs + 1;
                            }
                            else{
                                mins = mins - diff1;
                                hrs = hrs +1;
                            }
                        }
                        else if(hrS>11-diff){
                            hrs = hrs + diff - 12;
                            u=0;
//                            u=0;
                            if(minS<60-diff1){
                                mins = mins + diff1;
                            }
                            else {
                                mins = mins - 30;
                                hrs = hrs + 1;
                            }
                        }
                        else if(hrS==11-diff){
                            if(minS<60-diff1){
                                hrs = hrs + diff;
                                mins = mins + diff1;
//                                u=1;
                            }
                            else if(minS>60-diff1){
                                mins = mins - diff1;
                                hrs=0;
                                u=0;
                            }
                            else{
                                hrs = 0;
                                mins = 0;
                                u=0;
                            }
                        }
                    }
                    hrS=hrs;
                    if(u==0)
                        h = "am";
                    else if(u==1)
                        h = "pm";
//                        diff_hrs = Math.abs(hrs - hrS);
                    textView.setText(getString(R.string.India,hrs,mins,h));
                    break;
                case 1:
                    diff=6;
//                    if(mins>=60) {
//                        mins = mins - 60;
//                        hrs = hrs + 1;
//                    }
//                    if(mins<0) {
//                        mins += 60;
//                        hrs = hrs - 1;
//                    }
//                    if(hrs<0){
//                        hrs=hrs+24;
//                    }
//                    else if(hrs>24){
//                        hrs = hrs -24;
//                    }
//                    checker.check();
                    if(u==0) {
                        if(hrS<diff) {
                            hrs = hrs + 12 - diff;
                            u = 1;
                        }
                        else if(hrS>diff){
                            hrs = hrs - diff;
                        }
                        else {
                            hrs = 0;
                        }
                    }
                    else if(u==1){
                        if(hrS<diff) {
                            hrs = hrs + 12 - diff;
                            u = 0;
                        }
                        else if(hrS>diff){
                            hrs = hrs - diff;
                        }
                        else {
                            hrs = 12;
//                            u=0;
                        }
                    }
                    hrS=hrs;
                    if(u==0)
                        h = "am";
                    else if(u==1)
                        h = "pm";
//                    if(hrs>hrS)
//                        diff_hrs = hrs - hrS;
//                    else
//                        diff_hrs = hrS - hrs;
                    textView.setText(getString(R.string.Chicago,hrs,mins,h));
                    break;
                case 2:
                    diff = -11;
                    diff = Math.abs(diff);
//                    if(mins>=60) {
//                        mins = mins - 60;
//                        hrs = hrs + 1;
//                    }
//                    if(mins<0) {
//                        mins += 60;
//                        hrs = hrs - 1;
//                    }
//                    if(hrs<0){
//                        hrs=hrs+24;
//                    }
//                    else if(hrs>24){
//                        hrs = hrs -24;
//                    }
//                    checker.check();
                    if(u==0) {
                        if(hrS<12-diff) {
                            hrs = hrs + diff;
                        }
                        else if(hrS>12-diff){
                            hrs =  hrs + diff -12;
                            u = 1;
                        }
                        else {
                            hrs = 12;
                            u=1;
                        }
                    }
                    else if(u==1){
                        if(hrS<12-diff) {
                            hrs = hrs + diff;
                        }
                        else if(hrS>12-diff){
                            hrs = hrs - 12 + diff;
                            u=0;
                        }
                        else {
                            hrs = 0;
                            u=0;
                        }
                    }
                    hrS=hrs;
                    if(u==0)
                        h = "am";
                    else if(u==1)
                        h = "pm";
//                    if(hrs>hrS)
//                        diff_hrs = hrs - hrS;
//                    else
//                        diff_hrs = hrS - hrs;
                    textView.setText(getString(R.string.Melbourne,hrs,mins,h));
                    break;
                case 3:
                    diff=0;
//                    if(mins>=60) {
//                        mins = mins - 60;
//                        hrs = hrs + 1;
//                    }
//                    if(mins<0) {
//                        mins += 60;
//                        hrs = hrs - 1;
//                    }
//                    if(hrs<0){
//                        hrs=hrs+24;
//                    }
//                    else if(hrs>24){
//                        hrs = hrs -24;
//                    }
//                    checker.check();
//                    if(hrs>hrS)
//                        diff_hrs = hrs - hrS;
//                    else
//                        diff_hrs = hrS - hrs;
//                    diff_mins = mins - minS;
                    if(u==0) {
                        if(hrS<12-diff) {
                            hrs = hrs + diff;
                        }
                        else if(hrS>12-diff){
                            hrs =  hrs + diff -12;
                            u = 1;
                        }
                        else {
                            hrs = 12;
                            u=1;
                        }
                    }
                    else if(u==1){
                        if(hrS==0){
                            hrs=12;
                        }
                        else if(hrS<12-diff) {
                            hrs = hrs + diff;
                        }
                        else if(hrS>12-diff){
                            hrs = hrs - 12 + diff;
                            u=0;
                        }
                        else {
                            hrs = 12;
                            u=0;
                        }
                    }
                    textView.setText(getString(R.string.London,hrs,mins,h));
                    break;
                case 4:
                    diff = -2;
//                    if(mins>=60) {
//                        mins = mins - 60;
//                        hrs = hrs + 1;
//                    }
//                    if(mins<0) {
//                        mins += 60;
//                        hrs = hrs - 1;
//                    }
//                    if(hrs<0){
//                        hrs=hrs+24;
//                    }
//                    else if(hrs>24){
//                        hrs = hrs -24;
//                    }
//                    checker.check();
//                    if(hrs>hrS)
//                        diff_hrs = hrs - hrS;
//                    else
//                        diff_hrs = hrS - hrs;
                    diff = Math.abs(diff);
                    if(u==0) {
                        if(hrS<12-diff) {
                            hrs = hrs + diff;
                        }
                        else if(hrS>12-diff){
                            hrs =  hrs + diff -12;
                            u = 1;
                        }
                        else{
                            hrs =  12;
                            u = 1;
                        }
                    }
                    else if(u==1) {
                        if (hrS < 12 - diff) {
                            hrs = hrs + diff;
                        } else {
                            hrs = hrs - 12 + diff;
                            u = 0;
                        }
                    }
                    if(u==0)
                        h = "am";
                    else if(u==1)
                        h = "pm";
                    textView.setText(getString(R.string.Johannesburg,hrs,mins,h));
                    break;
            }
//            textView.setText(u+"");
//            textView.setText(mins + "");
            u=U;
            hrS=tHour;
            minS=tMin;
        });
    }
//    static class Checker{
//        public static void check(){
//            if(mins<0) {
//                mins += 60;
//                hrs = hrs - 1;
//            }
//            if(hrs<0){
//                hrs=hrs+24;
//            }
//            else if(hrs>24){
//                hrs = hrs -24;
//            }
//        }
//    }
}