package it.meal.unibz.mseunibzmeal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class BookDetailsActivity extends AppCompatActivity {
    private EditText BookName;
    private EditText AuthorName;
    private EditText Availability;

    private int bookID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        this.BookName = (EditText)findViewById(R.id.book_name);
        this.AuthorName = (EditText)findViewById(R.id.author_name);
        this.Availability = (EditText)findViewById(R.id.availability);
    }

}
