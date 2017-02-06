package it.meal.unibz.mseunibzmeal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MensaCamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensa_cam);

        /*
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://pro.unibz.it/mensawebcam"));
        startActivity(intent);
        */

        WebView myWebView = (WebView) findViewById(R.id.mensaCam);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://pro.unibz.it/mensawebcam");


    }

}
