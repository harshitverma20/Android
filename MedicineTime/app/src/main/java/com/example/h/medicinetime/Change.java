package com.example.h.medicinetime;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Change extends AppCompatActivity {
    EditText medname;
    TextView date , time;
    Button set_date ,set_time,set_data;

    Calendar cal;

    private int mYear , mMonth , mDay , mHour , mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        medname = findViewById(R.id.editText);
        date = findViewById(R.id.textView3);
        time = findViewById(R.id.textView5);
        set_date = findViewById(R.id.button);
        set_time = findViewById(R.id.button2);
        set_data = findViewById(R.id.button3);


        cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(Change.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth+ "-" +(monthOfYear+1)+ "-" +year);          //set to textview

                        cal.set(Calendar.YEAR , year);                                      //set for alarm
                        cal.set(Calendar.MONTH , monthOfYear);
                        cal.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                    }
                },mYear , mMonth , mDay);
                dpd.show();
            }
        });

        set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                TimePickerDialog tpd = new TimePickerDialog(Change.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        time.setText(hourOfDay + ":" +minute);

                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE , minute);
                        cal.set(Calendar.SECOND , 0);
                    }
                },mHour , mMinute , false);
                tpd.show();
            }
        });

        set_data.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(medname.getText().toString())){
                    medname.setError("Required");
                }
                else{

                    AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent in = new Intent(Change.this , Alarm.class);
                    PendingIntent pi= PendingIntent.getBroadcast(Change.this , 0,in , 0);

                    manager.setExact(AlarmManager.RTC_WAKEUP , cal.getTimeInMillis() , pi);

                    String medname_f = medname.getText().toString();
                    String date_f = date.getText().toString();
                    String time_f = time.getText().toString();

                    DBclass db = new DBclass(Change.this);
                    db.onAdd(medname_f ,date_f ,time_f );

                    Toast.makeText(Change.this , "Reminder added" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Change.this , MedicineReminder.class);
                    startActivity(intent);

                    sendNotification();
                }

            }
        });
    }

    public void sendNotification() {
        NotificationCompat.Builder mBuilder = new
                NotificationCompat.Builder(Change.this, "new_notify")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Medicine Reminder")
                .setContentText("It's medicine time!!");

        int mNotificationId = 001;
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //   notificationManager.notify(mNotificationId, mBuilder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("new_notify",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        long[] vibrate = {100,200,300,400};
        mBuilder.setVibrate(vibrate);

        notificationManager.notify(0, mBuilder.build());


    }
}
