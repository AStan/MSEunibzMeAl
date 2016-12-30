package it.meal.unibz.mseunibzmeal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BookDetailsActivity extends AppCompatActivity {
    private EditText BookName;
    private EditText AuthorName;
    private EditText Availability;
    private TableLayout tableLayout;

    private int bookID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        tableLayout=(TableLayout)findViewById(R.id.tblBookList);

        TextView textV = (TextView)findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();

        String searchValue = bundle.getString("Hello"); //search key

        textV.setText(searchValue);

        URL myURL;
        HttpURLConnection myURLConnection = null;
        try {

            String apikey = "l7xx53f22519810d4f56a21caceb0fc95de4";

            myURL = new URL("https://api-na.hosted.exlibrisgroup.com/primo/v1/pnxs?vid=UNIBZ&scope=All&q=any,contains,"+searchValue+"&apikey="+apikey);
            myURLConnection = (HttpURLConnection) myURL.openConnection();
            myURLConnection.setRequestMethod("GET");
            /*myURLConnection.setRequestProperty("Content-length", "0");
            myURLConnection.setUseCaches(false);
            myURLConnection.setAllowUserInteraction(false);
            myURLConnection.setConnectTimeout(100000);
            myURLConnection.setReadTimeout(100000);*/
            myURLConnection.connect();
            int status = myURLConnection.getResponseCode();

            InputStream in = myURLConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        }
        catch (Exception e) {
            // new URL() failed
            e.printStackTrace();
        }
        finally {
            if(myURLConnection != null) {
                myURLConnection.disconnect();
            }
        }
    }
}
