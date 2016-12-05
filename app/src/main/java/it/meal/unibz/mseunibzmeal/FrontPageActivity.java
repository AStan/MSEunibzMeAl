package it.meal.unibz.mseunibzmeal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FrontPageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_frontpage);

        Button signInButton= (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        Button roomsButton = (Button) findViewById(R.id.roomsButton);
        roomsButton.setOnClickListener(this);

        Button fywButton = (Button) findViewById(R.id.fywButton);
        fywButton.setOnClickListener(this);

        Button timetableButton = (Button) findViewById(R.id.timetableButton);
        timetableButton.setOnClickListener(this);

        Button libraryButton = (Button) findViewById(R.id.libraryButton);
        libraryButton.setOnClickListener(this);

    }

    public void onClick(View v) {

        if (v.getId() == R.id.signInButton) {
            Intent intent = new Intent(FrontPageActivity.this, SignInActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.roomsButton) {
            Intent intent = new Intent(FrontPageActivity.this, RoomsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.fywButton) {
            Intent intent = new Intent(FrontPageActivity.this, FindYourWayActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.timetableButton) {
            Intent intent = new Intent(FrontPageActivity.this, TimetableActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.libraryButton) {
            Intent intent = new Intent(FrontPageActivity.this, ViewBookActivity.class);
            startActivity(intent);

    }
}
}
