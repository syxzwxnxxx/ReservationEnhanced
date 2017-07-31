package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnReset;
    Button btnReserve;
    EditText etName;
    EditText etNo;
    EditText etSize;
    CheckBox cbSmoking;
    EditText etTime;
    EditText etDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReserve = (Button) findViewById(R.id.btnReserve);
        btnReset = (Button) findViewById(R.id.btnReset);
        etName = (EditText) findViewById(R.id.etName);
        etNo = (EditText) findViewById(R.id.etNo);
        etSize = (EditText) findViewById(R.id.etSize);
        cbSmoking = (CheckBox) findViewById(R.id.cbSmoking);
        etDay = (EditText) findViewById(R.id.etDay);
        etTime = (EditText) findViewById(R.id.etTime);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {
                etName.setText("");
                etNo.setText("");
                etSize.setText("");
                cbSmoking.setChecked(true);

            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating the Listener to set the time
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        etTime.setText(hourOfDay + ":" + minute);
                    }
                };

                // Create the Time Picker Dialog
//                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
//                        myTimeListener, 20, 00, true);

                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, hour, minute, true);

                myTimeDialog.show();
            }
        });

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                int year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH);
                int date = now.get(Calendar.DATE);

                // Creating the Listener to set the date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        etDay.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };

                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener, year, month, date );

                myDateDialog.show();
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                                String name = etName.getText().toString();
                                String mobile = etNo.getText().toString();
                                String size = etSize.getText().toString();

                                String smoking = "";
                                if (cbSmoking.isChecked()) {
                                    smoking = "Smoking Area";
                                } else {
                                    smoking = "Non-Smoking Area";
                                }

                                String message = "";
                                message = "Name: " + name +
                                        " \nMobile Number: " + mobile +
                                        " \nGroup Size: " + size
                                        + " \nArea: " + smoking +
                                        " \nDate: " + etDay.getText().toString() +  "\nTime: " + etTime.getText().toString();

                                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                                myBuilder.setTitle("Reservation complete");
                                myBuilder.setMessage(message);
                                myBuilder.setCancelable(true);
                                myBuilder.setPositiveButton("Close", null);

                                AlertDialog myDialog = myBuilder.create();
                                myBuilder.show();

                            }
                        });

    }

}
