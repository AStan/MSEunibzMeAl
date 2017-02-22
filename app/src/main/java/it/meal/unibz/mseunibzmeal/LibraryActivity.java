package it.meal.unibz.mseunibzmeal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class LibraryActivity extends AppCompatActivity implements View.OnClickListener{

    String searchV;
    //TextView textout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Search the Library");

        Button searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        EditText searchValue   = (EditText)findViewById(R.id.searchBooks);
        searchValue.setSelectAllOnFocus(true);
        searchValue.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                if (hasFocus)
                ((EditText)v).selectAll();
            }
        });


        if (v.getId()==R.id.searchButton) {

            Intent intent = new Intent(LibraryActivity.this, BookDetailsActivity.class);

            String searchV = searchValue.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("Hello", searchV);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}
