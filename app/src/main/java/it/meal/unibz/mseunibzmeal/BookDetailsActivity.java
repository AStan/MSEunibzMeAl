package it.meal.unibz.mseunibzmeal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.button;

    public class BookDetailsActivity extends AppCompatActivity {
        private String TAG = MainActivity.class.getSimpleName();

        private ProgressDialog progressDialog;
        public ListView listV;

        public TextView tv;
        //public static String url = "http://api.androidhive.info/contacts/";
        //public String url = "https://api-na.hosted.exlibrisgroup.com/primo/v1/pnxs?vid=UNIBZ&scope=All&q=any,contains, " + searchValue +"&apikey=l7xx53f22519810d4f56a21caceb0fc95de4"; //was static
        ArrayList<HashMap<String, String>> countryList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_book_details);

            countryList = new ArrayList<>();

            listV = (ListView)findViewById(R.id.listBooks);

            new FetchBookData().execute();
        }

        private class FetchBookData extends AsyncTask <Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(it.meal.unibz.mseunibzmeal.BookDetailsActivity.this);
                progressDialog.setMessage("Please Wait ...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                Bundle bundle = getIntent().getExtras();
                String searchValue = bundle.getString("Hello"); //search key
                //public static String url = "http://api.androidhive.info/contacts/";
                //public String url = "https://api-na.hosted.exlibrisgroup.com/primo/v1/pnxs?vid=UNIBZ&scope=All&q=any,contains, " + searchValue +"&apikey=l7xx53f22519810d4f56a21caceb0fc95de4"; //was static

                URL myURL = null;
                try {
                    myURL = new URL("https://api-na.hosted.exlibrisgroup.com/primo/v1/pnxs?vid=UNIBZ&scope=All&q=any,contains, " + searchValue +"&apikey=l7xx53f22519810d4f56a21caceb0fc95de4");
                } catch (Exception e){
                    e.printStackTrace();
                }

                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(String.valueOf(myURL));

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        JSONArray contacts = jsonObj.getJSONArray("docs");

                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

//                        String id = c.getString("id");
//                        String name = c.getString("name");
//                        String email = c.getString("email");

                            //date, availability, creator,title, callNumber, @TYPE

                            String date;

                            if (c.has("date")){
                                date = c.getString("date");
                            } else {
                                date = "Unspecified";
                            }

                            String creator;
                            if (c.has("creator")){
                                creator = c.getString("creator");
                            } else {
                                creator = "Unspecified";
                            }

                            String title = c.getString("title");
                            String type = c.getString("@TYPE");

                            HashMap<String, String> contact = new HashMap<>();
//                        contact.put("id", id);
//                        contact.put("name", name);
//                        contact.put("email", email);
                            contact.put("date", date);
                            contact.put("creator", creator);
                            contact.put("title", title);
                            contact.put("@TYPE", type);

                            countryList.add(contact);
                            Log.e("CountryList", countryList.toString());

                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "JSON parsing error:" + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "JSON parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                } else {
                    Log.e(TAG, "Could not get JSON");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Could not get JSON", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                return jsonStr;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(progressDialog.isShowing())
                    progressDialog.dismiss();

                //ListAdapter adapter = new SimpleAdapter(MainActivity.this, countryList, R.layout.cell, new String[]{"name", "email"}, new int[]{R.id.creator, R.id.title});
                ListAdapter adapter = new SimpleAdapter(BookDetailsActivity.this, countryList, R.layout.cell, new String[]{"creator", "title"}, new int[]{R.id.creator, R.id.title});

                listV.setAdapter(adapter);
            }

        }

        public class HttpHandler {
            private final String TAG = HttpHandler.class.getSimpleName();

            public HttpHandler(){}
            public String makeServiceCall(String reqUrl) {
                String response = null;
                try {
                    URL url = new URL(reqUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    response = convertStreamToString(in);
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }
            private String convertStreamToString(InputStream is) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return sb.toString();
            }
        }
    }