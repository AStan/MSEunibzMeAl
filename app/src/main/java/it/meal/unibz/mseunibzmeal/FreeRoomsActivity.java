package it.meal.unibz.mseunibzmeal;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FreeRoomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_rooms);

//        WebView myWebView = (WebView) findViewById(R.id.webviewRooms);
//        WebSettings webSettings = myWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        myWebView.loadUrl("http://aws.unibz.it/risweb/timetable.aspx");

        Button btnLoad = (Button) findViewById(R.id.btnLoad);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String siteUrl = "http://aws.unibz.it/risweb/timetable.aspx?start=10.01.2017&end=10.01.2017&showtype=0";
                try {

                    ArrayList listTimes = new ArrayList();
                    ArrayList listRooms = new ArrayList();

                    Document doc = Jsoup.connect(siteUrl).userAgent("Mozilla").data("name", "jsoup").get();
                    Elements times = doc.getElementsByClass("tt_time");
                    Elements rooms = doc.getElementsByClass("tt_room");

                    //td.tt_time
                    //td.tt_room
                    for (Element time : times) {
                        //selects the text between the tags <td class="tt_time">
                        listTimes.add(time.text());
                    }

                    for (Element room : rooms) {
                        //selects the text between the tags <td class="tt_room">
                        listRooms.add(room.text());
                    }



                    int sizeOfTimes = listTimes.size();
                    HashMap<String, String> finalList = new HashMap<String, String>();
                    String ser = "Ser-";

                    for(int j=0;j<sizeOfTimes;j++){

                        // ROOMS label format looks like: Exxx, Dyyy(E and D buildings), Fy.yy & Cx.xx
                        String tmp = listRooms.get(j).toString();

                        if(tmp.contains(ser)){
                            //check whether is it an E-floor; otherwise drop
                            if(tmp.startsWith("E")){
                                //clean from the useless things
                                String[] splitRoom = listRooms.get(j).toString().split("\\s+");
                                String[] splitTime = listTimes.get(j).toString().split("\\s+");
                                String cleanTime = splitTime[0]+"_"+splitTime[1];
                                finalList.put(splitRoom[0],cleanTime);
                            }
                        }
                    }


                    List<String> roomsList = Arrays.asList("E210","E211","E220","E221","E222","E223","E231","E320","E322","E323","E311","E312","E331","E420","E422","E423","E411","E412","E431","E520","E521","E522","E511","E531");

                    for(int i=0;i<roomsList.size();i++){
                        if(finalList.containsKey(roomsList.contains(String.valueOf(i)))){
                            //this means that the room occupied
                            roomsList.remove(i);
                        }
                    }

                /*
                Version1:
                FREE ROOMS all day:
                BUSY ROOMS:
                 */
                /*
                SHOW FLOOR BY FLOOR:
                - E2
                - E3
                - E4
                - E5
                */

                /*
                //Version 2:

                CASE 0:                    room is free from 08:00 to 20:00
                CASE 1:                    room is free only in few time spans
                CASE 2:                    room is busy all day

                //ATTENTION!  --:-- means until 20:00

                */




                    //testing output
                    TextView textViewTest = (TextView)findViewById(
                            R.id.textViewTest);
                    textViewTest.setText("Free Rooms ALL DAY LONG: 08:00-20:00 :\n\n"+roomsList.toString()+"\n\n\n"+"Occupied Rooms\n\n"+finalList.toString());
                    textViewTest.setMovementMethod(new ScrollingMovementMethod());






                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });

    }

}
