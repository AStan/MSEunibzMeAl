package it.meal.unibz.mseunibzmeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LibraryActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Button searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId()==R.id.searchButton) {
            Intent intent = new Intent(LibraryActivity.this, BookDetailsActivity.class);
            startActivity(intent);
        }
    }

}
