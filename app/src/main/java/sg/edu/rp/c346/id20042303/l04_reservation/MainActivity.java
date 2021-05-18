package sg.edu.rp.c346.id20042303.l04_reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextView name;
    TextView number;
    TextView groupsize;
    DatePicker rDate;
    TimePicker rTime;
    CheckBox smokingArea;
    Button cfmBtn;
    Button resetBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        groupsize = findViewById(R.id.groupsize);
        rDate = findViewById(R.id.rDate);
        rTime = findViewById(R.id.rTime);
        smokingArea = findViewById(R.id.smokingBox);
        cfmBtn = findViewById(R.id.cfmBtn);
        resetBtn = findViewById(R.id.resetBtn);

        rDate.updateDate(2020, 5, 1);
        rTime.setCurrentMinute(30);
        rTime.setCurrentHour(19);

        cfmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !number.getText().toString().isEmpty() && !groupsize.getText().toString().isEmpty()) {
                    String output = "Name: " + name.getText() + "\r";
                    output += "\n" + "Number: " + number.getText();
                    output += "\n" + "Group Size: " + groupsize.getText();
                    output += "\n" + "Date: " + rDate.getDayOfMonth() + "/" + (rDate.getMonth() + 1) + "/" + rDate.getYear();
                    if(rTime.getCurrentHour() > 12){
                        output += String.format("\n Time: %s:%sPM",rTime.getCurrentHour() - 12,rTime.getCurrentMinute());
                    }else{
                        output += "\n" + "Time: " + rTime.getCurrentHour() + ":" + rTime.getCurrentMinute() + "AM";
                    }
                    if (smokingArea.isChecked()) {
                        output += "\n" + "Table Area: " + "Smoking Area";
                    } else {
                        output += "\n" + "Table Area: " + "Non-Smoking Area";
                    }
                    Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(MainActivity.this, "Please fill the everything", Toast.LENGTH_SHORT).show();
                }


            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                number.setText("");
                groupsize.setText("");
                rDate.updateDate(2020, 5, 1);
                rTime.setCurrentMinute(30);
                rTime.setCurrentHour(19);
                smokingArea.setChecked(false);
            }
        });

        rTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (rTime.getCurrentHour() >= 21) {

                    rTime.setCurrentHour(20);
                    rTime.setCurrentMinute(59);
                    Toast.makeText(MainActivity.this, "We close at 9PM", Toast.LENGTH_SHORT).show();

                } else if (rTime.getCurrentHour() < 8) {
                    rTime.setCurrentHour(8);
                    rTime.setCurrentMinute(00);
                    Toast.makeText(MainActivity.this, "We open at 8AM", Toast.LENGTH_SHORT).show();
                }

            }
        });

        rDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (rDate.getMonth() < 5 && rDate.getYear() == 2020) {
                    rDate.updateDate(2020, 5, 1);
                    Toast.makeText(MainActivity.this, "Please choose a Date after today", Toast.LENGTH_SHORT).show();
                }else if(rDate.getYear() < 2020){
                    rDate.updateDate(2020, 5, 1);
                    Toast.makeText(MainActivity.this, "Please choose a Date after today", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}