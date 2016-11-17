package it.meal.unibz.mseunibzmeal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ViewBookActivity extends Activity {

    private ListView listBook;
    //db and db connect
    private EditText id;
    private String bookID = "";
    private Button searchBook;
    private ArrayList<String> bookName = new ArrayList<String>();
    private ArrayList<String> allBookIds = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_book);
        try {
            id = (EditText) findViewById(R.id.SearchBookID);
            searchBook = (Button) findViewById(R.id.btnSearchBook);
            listBook = (ListView) findViewById(R.id.listOfBooks);
            searchBook.setOnClickListener(new clickHandler());
        } catch (Exception ex) {

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        try {
            //open connection to db
            switch (item.getItemId()) {
                case R.id.edit_option_menu: {
                    //edit book from db
                    break;
                }
                case R.id.delete_option_menu: {
                    //del book from db
                    break;
                }
                case R.id.details_option_menu: {

                    break;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return true;
    }

    public class clickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                //open connection to db
                switch (v.getId()) {
                    case R.id.btnSearchBook: {
                        //show books with specified id (query to db)
                        //move to next item in list
                        //or display if no book found
                        break;
                    }
                }

            }
            catch (Exception ex) {

            }
        }
    }
}
