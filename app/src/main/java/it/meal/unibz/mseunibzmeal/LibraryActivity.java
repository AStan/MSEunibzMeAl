package it.meal.unibz.mseunibzmeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LibraryActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Button searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        EditText searchValue   = (EditText)findViewById(R.id.searchBooks);
        //TextView textView = (TextView)findViewById(R.id.textView3);



        if (v.getId()==R.id.searchButton) {

            Intent intent = new Intent(LibraryActivity.this, BookDetailsActivity.class);

            String searchV = searchValue.getText().toString();
            //textView.setText(searchV);

            //perform search with this query
            //then, get the list (as JSON?) and pass it to BookDetailsActivity, creating the list view


            Bundle bundle = new Bundle();
            bundle.putString("Hello", searchV);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }

}
