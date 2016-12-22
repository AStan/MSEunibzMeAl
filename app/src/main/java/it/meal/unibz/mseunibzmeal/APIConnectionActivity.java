package it.meal.unibz.mseunibzmeal;

import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class APIConnectionActivity {

    /*public JSONArray GetBooks() {
        String url = "https://api-na.hosted.exlibrisgroup.com/primo/v1/pnxs?vid=UNIBZ&scope=All&q=any,contains,Hello&apikey=l7xx53f22519810d4f56a21caceb0fc95de4";
        HttpEntity httpEntity = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray = null;
        try {
            String entityResponse = EntityUtils.toString(httpEntity);
            Log.e("Entity Response : ", entityResponse);
            JSONObject jsonObject = new JSONObject(entityResponse);
            jsonArray = jsonObject.getJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("JSONARRAY : ", String.valueOf(jsonArray));

        return jsonArray;
 */   }
