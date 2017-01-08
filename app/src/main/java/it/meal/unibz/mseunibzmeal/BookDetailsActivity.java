package it.meal.unibz.mseunibzmeal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BookDetailsActivity extends AppCompatActivity {

    ListView listView;

    Button button;
    TextView textView;

    String jsonString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        listView=(ListView)findViewById(R.id.listBooks);

        textView = (TextView)findViewById(R.id.data);
        textView.setTextIsSelectable(true);

        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new FetchBookData().execute();
            }
        });

    }

    private class FetchBookData extends AsyncTask <Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
        //TextView textV = (TextView)findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        String searchValue = bundle.getString("Hello"); //search key
        //textV.setText(searchValue);

        //URL myURL;

        HttpURLConnection myURLConnection = null;
        BufferedReader bufferedReader = null;

        //Will contain raw JSON response as string
        String bookDataJsonStr = null;

        try {
            String apikey = "l7xx53f22519810d4f56a21caceb0fc95de4";

            URL myURL = new URL("https://api-na.hosted.exlibrisgroup.com/primo/v1/pnxs?vid=UNIBZ&scope=All&q=any,contains,"+searchValue+"&apikey="+apikey);
            myURLConnection = (HttpURLConnection) myURL.openConnection();
            myURLConnection.setRequestMethod("GET");
            myURLConnection.connect();

            InputStream inputStream = myURLConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String data;
            while ((data = bufferedReader.readLine()) != null) {
                stringBuffer.append(data + "\n");
            }

            if (stringBuffer.length() == 0) {
                //Empty Stream, no need to parse
                return null;
            }

            bookDataJsonStr = stringBuffer.toString();
            return bookDataJsonStr;

        }
        catch (Exception e) {
            // new URL() failed
            Log.e("PlaceholderFragment", "Error", e);
            return new String("Exception: " + e.getMessage());
        }
        finally {
            if(myURLConnection != null) {
                myURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
    }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
            Log.i("json", s);

            jsonString = s;
        }
    }
}
