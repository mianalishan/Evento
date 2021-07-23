package com.example.evento;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Event_deatil extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		final Mysql sql=new Mysql(this);
		TextView title=(TextView)findViewById(R.id.title);
		TextView date=(TextView)findViewById(R.id.date);
		TextView time=(TextView)findViewById(R.id.time);
		TextView add=(TextView)findViewById(R.id.add);
		title.setText(getIntent().getExtras().getString("title"));
		date.setText(getIntent().getExtras().getString("date"));
		time.setText(getIntent().getExtras().getString("time"));
		add.setText(getIntent().getExtras().getString("location"));
		Button del=(Button)findViewById(R.id.done);
		del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	sql.deleteContact(Integer.parseInt(getIntent().getExtras().getString("id")));
            	 Intent i=new Intent(Event_deatil.this,Event_list.class);
            	 i.putExtra("id", "0");
            	 startActivity(i);
            	  
            	
            }
        });
		
		
	}
}
