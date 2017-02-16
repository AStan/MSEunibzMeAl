package it.meal.unibz.mseunibzmeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.content_home_page);

        Button mensaCamButton= (Button) findViewById(R.id.mensaCamButton);
        mensaCamButton.setOnClickListener(this);

        Button roomsButton = (Button) findViewById(R.id.roomsButton);
        roomsButton.setOnClickListener(this);

        Button fywButton = (Button) findViewById(R.id.fywButton);
        fywButton.setOnClickListener(this);

        Button timetableButton = (Button) findViewById(R.id.eventsButton);
        timetableButton.setOnClickListener(this);

        Button libraryButton = (Button) findViewById(R.id.libraryButton);
        libraryButton.setOnClickListener(this);

        Button other = (Button) findViewById(R.id.other);
        other.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.mensaCamButton) {
            //Intent intent = new Intent(HomePageActivity.this, SignInActivity.class);
            Intent intent = new Intent(MainActivity.this, MensaCamActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.roomsButton) {
            //Intent intent = new Intent(HomePageActivity.this, RoomsActivity.class);
            Intent intent = new Intent(MainActivity.this, FreeRoomsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.fywButton) {
            //Intent intent = new Intent(HomePageActivity.this, FindYourWayActivity.class);
            Intent intent = new Intent(MainActivity.this, DisplayEventsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.eventsButton) {
            //Intent intent = new Intent(HomePageActivity.this, TimetableActivity.class);
            Intent intent = new Intent(MainActivity.this, DisplayEventsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.libraryButton) {
            //Intent intent = new Intent(HomePageActivity.this, ViewBookActivity.class);
            Intent intent = new Intent(MainActivity.this, LibraryActivity.class);
            startActivity(intent);
        }
        else  if(v.getId() == R.id.other){
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
        }
    }
}
