package it.meal.unibz.mseunibzmeal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.test.espresso.core.deps.guava.collect.ArrayListMultimap;
import android.support.test.espresso.core.deps.guava.collect.Multimap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class FreeRoomsActivity extends AppCompatActivity{

    Button buttonGetDate;
    Button backButton;
    Button loadButton;
    Button shareButton;

    String currentDate;
    ListView timetable;
    DatePickerDialog datePickerDialog;

    String url;
    static ArrayList<String> converted;
    ArrayList<String> freeRooms;
    String date;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFreeRooms(ArrayList<String> freeRooms) {
        this.freeRooms = freeRooms;
    }

    private String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_rooms);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Free Rooms");

        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonGetDate = (Button) findViewById(R.id.buttonGetDate);
        //Get the full current date and time
        Calendar calendar = Calendar.getInstance();
        String format = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        currentDate = simpleDateFormat.format(calendar.getTime());
        buttonGetDate.setText(currentDate);
        setUrl(composeUrl(currentDate));
        findFreeRooms(this.url);


        buttonGetDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(FreeRoomsActivity.this, new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }
        });

        //Where everything begins
        loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String url = composeUrl(getDate());
            findFreeRooms(url);

            //populating the ListView
                populate();
            }

            });

        timetable = (ListView) findViewById(R.id.listTimetable);
        populate();

        shareButton = (Button) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, converted.toString());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Send using:"));
            }
        });
    }



    public void populate() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.roomdata,R.id.RoomNo,freeRooms);
        timetable.setAdapter(arrayAdapter); }
    /**
     * Find Free Rooms - MAIN FUNCTION
     * @param
     */
    private void findFreeRooms(String url) {

        Multimap<String, String> busyRooms = fetchRooms(url);
        Rooms rooms = populateRooms(busyRooms);
        Multimap<String, ArrayList<String>> freeRooms = computeFreeTimeSlots(rooms);
        setFreeRooms(convertFreeRoomsToArrayList(freeRooms));
    }

    public class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // getCalender();
            /*
            int mYear = year;
            int month = monthOfYear;
            int mDay = dayOfMonth;
            */

            int mYear = year;
            int month = monthOfYear+1;
            String formattedMonth = ""+month;
            String formattedDayOfMonth = ""+dayOfMonth;
            if (month<10){
                formattedMonth = "0"+month;
            }
            if (dayOfMonth < 10){
                formattedDayOfMonth = "0" + dayOfMonth;
            }

            String date = formattedDayOfMonth+"."+formattedMonth+"."+year;
            buttonGetDate.setText(formattedDayOfMonth+"."+formattedMonth+"."+year);
            setDate(date);

            /*
            String date = mDay+"."+(month+1)+"."+mYear;
            buttonGetDate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay).append(".").append(month+1).append(".")
                    .append(mYear));
            System.out.println(buttonGetDate.getText().toString());
            setDate(date);
            */
        }
    }

    private String composeUrl(String date){
        String URL = "http://aws.unibz.it/risweb/timetable.aspx?start="+date+"&end="+date+"&showtype=0";
        return URL;
    }

    /**
     * Fetch Rooms and return the valid busy rooms for that day
     */
    public static Multimap<String, String> fetchRooms(String url){

        //String siteUrl = "http://aws.unibz.it/risweb/timetable.aspx?start=08.03.2017&end=08.03.2017&showtype=0";

        ArrayList listTimes;
        ArrayList listRooms;
        Multimap<String, String> busyRooms = ArrayListMultimap.create();

        try {

            listTimes = new ArrayList();
            listRooms = new ArrayList();
            Document doc = Jsoup.connect(url).userAgent("Mozilla").data("name", "jsoup").get();
            Elements times = doc.getElementsByClass("tt_time");
            Elements rooms = doc.getElementsByClass("tt_room");
            //td.tt_time & td.tt_room
            //selects the text between the tags <td class="tt_time">
            for (Element time : times) {
                listTimes.add(time.text());
            }
            //selects the text between the tags <td class="tt_room">
            for (Element room : rooms) {
                listRooms.add(room.text());
            }

            int sizeOfTimes = listTimes.size();
            //https://google.github.io/guava/releases/16.0/api/docs/com/google/common/collect/Multiset.html

            final List<String> roomsList = Arrays.asList("E211","E212","E220","E221","E223","E231","E311","E312","E320","E321","E322","E323","E331","E411","E412","E420","E421","E423","E431","E511","E520","E521","E522","E523","E531");
            int sizeRL = roomsList.size();

            for(int j=0;j<sizeOfTimes;j++){
                // ROOMS label format looks like: Exxx, Dyyy(E and D buildings), Fy.yy & Cx.xx
                String tmp = listRooms.get(j).toString();
                for(int k=0;k<sizeRL;k++){
                    //check if there are lectures in a room of the ROOM LIST. Selects only among those rooms.
                    if(tmp.contains(roomsList.get(k))){

                        //clean from the useless things
                        String[] splitRoom = listRooms.get(j).toString().split("\\s+");
                        String[] splitTime = listTimes.get(j).toString().split("\\s+");

                        //CHECK for wrong-formatted time (e.g. 14:15-16:15)
                        splitTime[0] = checkTimeFormat(splitTime[0]);
                        splitTime[1] = checkTimeFormat(splitTime[1]);

                        String cleanTime = splitTime[0]+"-"+splitTime[1];

                        busyRooms.put(splitRoom[0].substring(0,4),cleanTime);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return busyRooms;
    }

    /**
     * Checks whether the time is different from the expected format 08-20 & :00 || :30
     * @param time
     */
    public static String checkTimeFormat(String time){
        String correctFormat = "";
        String[] spl = time.split(":");
        //check HOURS

        //check MINUTES
        if((!spl[1].equals("00"))||(!spl[1].equals("30"))){
            int value = Integer.parseInt(spl[1]);
            if((value>=0)&&(value<30)){
                if(value>15){
                    correctFormat = spl[0]+":30";
                }
                else
                    correctFormat = spl[0]+":00";
            }
            else
                if(value>=30){
                    if(value>45){
                        correctFormat = spl[0]+":00";
                    }
                    else
                        correctFormat = spl[0]+":30";
                }

        }

        return correctFormat;
    }

    /**
     * Creates Rooms and populates them with busy time (retrieved from fetchRooms).
     *
     * @param busyRooms
     * @return
     */
    public static  Rooms populateRooms(Multimap<String, String> busyRooms){

        Rooms rooms = new Rooms();
        int roomlength = rooms.getRoomsLength();

        //sort the busyRooms
        Collection<String> coll = busyRooms.keySet();
        List<String> list = new ArrayList<>(coll);
        Collections.sort(list);

        //start of the iteration over the busy list of rooms
        for(int i=0;i<coll.size();i++){

            //start of the iteration over the default list of rooms
            for(int j=0;j<roomlength;j++){

                //if the room NAME in the default list of rooms == the room NAME of the busyRooms
                if(rooms.getRoom(j).getRoomName().equals(list.get(i))){
                    //allocate busy time slots

                    //get the values(occupied time slots) of the current busy room
                    Collection<String> valz = busyRooms.get(list.get(i));
                    Object[] a = valz.toArray();
                    for(int k=0;k<a.length;k++){
                        //addBusyTime
                        rooms.getRoom(j).addOccupiedTime(a[k].toString());
                    }
                }
            }
        }
        return rooms;
    }

    /**
     * Given the rooms with allocated busy times (lecture hours), generates a list with the ROOM NAME and FREE TIME SLOTS of each
     * @param rooms
     * @return
     *
     */
    public static  Multimap<String, ArrayList<String>> computeFreeTimeSlots(Rooms rooms){

        int rmLngth = rooms.getRoomsLength();
        String roomname = "";
        ArrayList<String> arrayList = new ArrayList<>();
        Multimap<String, ArrayList<String>> freeRooms = ArrayListMultimap.create();

        for(int i=0;i<rmLngth;i++){
            roomname = rooms.getRoom(i).getRoomName();
            arrayList = rooms.getRoom(i).getTimeSlot().getFreeTime();
            freeRooms.put(roomname,arrayList);
        }
        return freeRooms;
    }

    /**
     * Displays the Free rooms and their available time slots
     * @param freeRooms
     */
    /*private void displayFreeRooms(Multimap<String, ArrayList<String>> freeRooms){

        //sort the Multimap
        Collection<String> coll = freeRooms.keySet();
        List<String> list = new ArrayList<>(coll);
        Collections.sort(list);

        //iterate through the object freeRooms, to display for each ROOM what are the free time slots
        for(int j=0;j<coll.size();j++){
            System.out.println("Room: "+list.get(j));
            Collection<ArrayList<String>> valz = freeRooms.get(list.get(j));
            Object[] a = valz.toArray();
            for(int k=0;k<a.length;k++){
                System.out.println("Values: "+a[k]);
            }
            ArrayList<String> roomDetails = new ArrayList<String>();
            for (String s : roomDetails) {

            }
        }

    }*/

    /**
     * Converts multimap to an ArrayList. Can be useful for feeding it to a ListView
     * @return
     */
    public static  ArrayList<String> convertFreeRoomsToArrayList(Multimap<String, ArrayList<String>> freeRooms){
        //sort the Multimap
        Collection<String> coll = freeRooms.keySet();
        List<String> list = new ArrayList<>(coll);
        Collections.sort(list);

        converted = new ArrayList<>();

        //iterate through the object freeRooms

        /*for(int j=0;j<coll.size();j++){
            converted.add(list.get(j));
            Collection<ArrayList<String>> valz = freeRooms.get(list.get(j));
            Object[] a = valz.toArray();
            for(int k=0;k<a.length;k++){
                converted.add(a[k].toString());
            }
        }*/
        /*for(int j=0;j<coll.size();j++){
            Collection<ArrayList<String>> valz = freeRooms.get(list.get(j));
            Object[] a = valz.toArray();
            for(int k=0;k<a.length;k++){
                converted.add(list.get(j).toString()+"   "+a[k].toString());
            }
        }*/

        for(int j=0;j<coll.size();j++){
            Collection<ArrayList<String>> valz = freeRooms.get(list.get(j));
            Object[] a = valz.toArray();
            for(int k=0;k<a.length;k++){
                converted.add("Room Name: "+list.get(j).toString()+" \n\nFree Times: "+a[k].toString());
            }
        }

        return converted;
    }

}
