package it.meal.unibz.mseunibzmeal;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ReserveBookActivity extends Activity {

    private Bundle bundle;
    private String BookId="";
    private String BookName="";
    private String StudentID="";
    private EditText ReserveBookID,ReserveBookName;
    private DatePicker ReserveDate;
    private Button ReserveBook;
    //database connection
    public  String date;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.reservebook);

        bundle = getIntent().getExtras();

        BookId = bundle.getString("Book_ID");
        BookName = bundle.getString("Book_Name");
        StudentID=bundle.getString("User");
        //ReserveBookName=(EditText)findViewById(R.id.etReserveBookName);
        //ReserveBookID=(EditText)findViewById(R.id.etReserveBookID);
        ReserveBookID.setText(BookId);
        ReserveBookName.setText(BookName);
        //ReserveBook=(Button)findViewById(R.id.btnReserveBook);
        ReserveBook.setOnClickListener(new clickHandler());

        //connect to db
    }
    public class clickHandler implements View.OnClickListener{
        @Override
        public void onClick(View view){

            try{
                //CREATE TABLE to ReserveBook by book id, student id, book name and date
                Toast.makeText(getApplicationContext(),"Book Reserverd",Toast.LENGTH_LONG).show();
            }
            catch (Exception ex){
                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
