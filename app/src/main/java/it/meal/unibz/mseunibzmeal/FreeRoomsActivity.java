package it.meal.unibz.mseunibzmeal;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FreeRoomsActivity extends AppCompatActivity {

    Button buttonGetDate;
    Button backButton;
    ListView timetable;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_rooms);

        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(this, YourMainActivity.class);
                //startActivity(intent);
                finish();
            }
        });

        buttonGetDate = (Button) findViewById(R.id.buttonGetDate);
        //Get the full current date and time
        String dt;
        Date cal = (Date) Calendar.getInstance().getTime();
        dt = cal.toLocaleString();
        buttonGetDate.setText(dt.toString());
        buttonGetDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                //System.out.println("the selected " + mDay);

                /*String myFormat = "dd MMMM yyyy"; // your format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                buttonGetDate.setText(sdf.format(c.getTime()));*/

                DatePickerDialog dialog = new DatePickerDialog(FreeRoomsActivity.this,
                        new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }
        });


        timetable = (ListView) findViewById(R.id.listTimetable);

        new FetchTable().execute();


    }

    public class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            buttonGetDate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay).append("/").append(mMonth + 1).append("/")
                    .append(mYear).append(" "));
            System.out.println(buttonGetDate.getText().toString());
        }
    }

    private class FetchTable extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //ListAdapter adapter = new SimpleAdapter(MainActivity.this, countryList, R.layout.cell, new String[]{"name", "email"}, new int[]{R.id.creator, R.id.title});
            ListAdapter adapter = new SimpleAdapter(FreeRoomsActivity.this, arrayList, R.layout.roomdata, new String[]{"Room Number", "Time"}, new int[]{R.id.RoomNo, R.id.TimeSlot});

            timetable.setAdapter(adapter);
        }
    }

}
