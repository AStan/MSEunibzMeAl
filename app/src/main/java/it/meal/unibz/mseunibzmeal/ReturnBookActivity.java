package it.meal.unibz.mseunibzmeal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ReturnBookActivity extends AppCompatActivity {

    //db
    private ListView listviewreturnbook;
    ArrayList<getter_setter> bookinfo=new ArrayList<getter_setter>();
    ArrayList<String> allBookId=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private TextView bookname,reservedate;//,studentname;
    private String bookID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_returnbook);
        try{
            listviewreturnbook=(ListView)findViewById(R.id.listViewStudentReturn);
            //open connection to db
            //set reservation data
            //display if book not found

            //get book info
            //change status of book to returned
            //add to returned books table on db

    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        try{
            switch (item.getItemId()){
                case R.id.return_book_option:{
                    // get data from getters/setters
                    // /if book is available
                    //update db
                    //delete entry from db
                    Toast.makeText(getApplicationContext(),"Book Returned",Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }
    @Override
    public void onCreateContextMenu (ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_returnbook, menu);
    }
    public class getter_setter {
        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        private String bookname;

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        private String studentname;

        public String getReserveddate() {
            return reserveddate;
        }

        public void setReserveddate(String reserveddate) {
            this.reserveddate = reserveddate;
        }

        private String reserveddate;

    }
}
