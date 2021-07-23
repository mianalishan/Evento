package com.example.evento;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add_new extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new);
		final Mysql sql=new Mysql(this);
		final Context c=this.getApplicationContext();
		final EditText title=(EditText)findViewById(R.id.editText1);
		final EditText time=(EditText)findViewById(R.id.editText2);
		final EditText date=(EditText)findViewById(R.id.editText3);
		final EditText address=(EditText)findViewById(R.id.editText4);
		final Button done=(Button)findViewById(R.id.done);
		
		
		
		done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	try{
                
                sql.insertContact(title.getText().toString(), date.getText().toString(), time.getText().toString(), "ali", address.getText().toString());
                Toast.makeText(c,"insert into database", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Add_new.this,MainActivity.class);
               startActivity(i);
                ArrayList<String> todoItems =sql.getAllCotacts();
                Toast.makeText(c,todoItems.get(0), Toast.LENGTH_LONG).show();
            	}catch(Exception e)
            	{
            		 Toast.makeText(c,e.getMessage(), Toast.LENGTH_SHORT).show();
            	}
                
            }
        });
		
	
	
	
}
	

}
