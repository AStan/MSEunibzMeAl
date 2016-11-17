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
            getter_setter temp=new getter_setter();
            dB=new dBConnection(getApplicationContext());
            sDatabase=dB.Open();
            Cursor cursor =sDatabase.rawQuery("Select * from tbl_ReserveBook JOIN tbl_Student ON tbl_ReserveBook.studentID=tbl_Student.studentID",null);
            if(cursor.moveToFirst()){
                do{
                    temp.setBookname(cursor.getString(cursor.getColumnIndex("BookName")));
                    temp.setStudentname(cursor.getString(cursor.getColumnIndex("StudentName")));
                    temp.setReserveddate(cursor.getString(cursor.getColumnIndex("ReservedDate")));
                    bookinfo.add(temp);
                    allBookId.add(cursor.getString(cursor.getColumnIndex("BookID")));
                }
                while (cursor.moveToNext());
            }
            else{
                Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_LONG).show();
            }
            try{
                adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_2,android.R.id.text1,bookinfo){
                    @Override
                    public View getView(int position,View convertView,ViewGroup parent){
                        parent.setBackgroundColor(Color.BLUE);
                        View view=super.getView(position,convertView,parent);
                        bookname=(TextView)view.findViewById(android.R.id.text1);
                        reservedate=(TextView)view.findViewById(android.R.id.text2);
                        // Toast.makeText(getApplicationContext(),bookinfo.get(position).getBookname()+bookinfo.get(position).getStudentname(),Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(),bookinfo.get(position).getReserveddate(),Toast.LENGTH_LONG).show();
                        bookname.setText(bookinfo.get(position).getBookname());
                        reservedate.setText(bookinfo.get(position).getStudentname()+"     "+bookinfo.get(position).getReserveddate());
                        return view;
                    }
                };
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
            }

            listviewreturnbook.setAdapter(adapter);
            listviewreturnbook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(AdapterView<?>parent,View view,int position,long id){
                    bookID=allBookId.get(position);
                    registerForContextMenu(listviewreturnbook);

                    bookinfo.remove(position);
                    adapter.notifyDataSetChanged();
                    return  false;
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        try{
            switch (item.getItemId()){
                case R.id.return_book_option:{
                    //if book is available
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
