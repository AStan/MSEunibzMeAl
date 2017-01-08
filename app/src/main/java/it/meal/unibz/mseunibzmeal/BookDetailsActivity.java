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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
<<<<<<< HEAD
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
=======
        tableLayout=(TableLayout)findViewById(R.id.tblBookList);
>>>>>>> parent of 5b849d2... json implementation

        TextView textV = (TextView)findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();

        String searchValue = bundle.getString("Hello"); //search key

        textV.setText(searchValue);
    }

    private void sendGet() throws Exception {

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
            myURLConnection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String data;

            int status = myURLConnection.getResponseCode();
            JSONObject jsonObject = new JSONObject();

            while ((data = bufferedReader.readLine()) != null) {
                stringBuilder.append(data);
            }
            bufferedReader.close();


            if (myURLConnection.getResponseCode()==201 || myURLConnection.getResponseCode()==200) {
                //get json object
                textV.setText(data);
            }

<<<<<<< HEAD
            bookDataJsonStr = stringBuffer.toString();
            return bookDataJsonStr;

=======
>>>>>>> parent of 5b849d2... json implementation
        }
        catch (Exception e) {
            // new URL() failed
            e.printStackTrace();
        }
        finally {
            if(myURLConnection != null) {
                myURLConnection.disconnect();
            }
<<<<<<< HEAD
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

            //jsonString = s;
=======
>>>>>>> parent of 5b849d2... json implementation
        }
    }
}
