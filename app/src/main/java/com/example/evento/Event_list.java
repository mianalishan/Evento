package com.example.evento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Event_list extends Activity {
	 public ArrayList<String> todoItems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);
		try{
		 todoItems = new ArrayList<String>();
		final Mysql sql=new Mysql(this.getApplicationContext());
		if(getIntent().getExtras().getString("id").equals("0"))
		{
		 todoItems=sql.getAllCotacts();
		}else{
			todoItems=sql.todayEvent();
		}
		//Toast.makeText(getApplicationContext(),todoItems.get(0), Toast.LENGTH_SHORT).show();
		 final ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, todoItems);
	      
	      final ListView listView = (ListView) findViewById(R.id.mobile_list);
	      listView.setAdapter(adapter);
	      
	      final ArrayList<String> arr[]=sql.getAllEvent();
	      listView.setOnItemClickListener(new OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		  //Toast.makeText(getApplicationContext(), "clicked"+arr[5].get(position), Toast.LENGTH_LONG).show();
	    	    Intent i=new Intent(Event_list.this,Event_deatil.class);
	    	    i.putExtra("title", arr[0].get(position));
	    	    i.putExtra("date", arr[1].get(position));
	    	    i.putExtra("time", arr[2].get(position));
	    	    i.putExtra("location", arr[4].get(position));
	    	    i.putExtra("id",arr[5].get(position) );
	    	    
	    	    startActivity(i);
	    	  }
	    	}); 

	      Button today=(Button)findViewById(R.id.today);
	      Button all=(Button)findViewById(R.id.all);
	      today.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	try{
	            	
	            	
	            	 
	              
	                Intent i=new Intent(Event_list.this,Event_list.class);
	                i.putExtra("id", "1");
	                startActivity(i);
	            	}catch(Exception e)
	            	{
	            		Toast.makeText(getApplicationContext(),e.getMessage()+"today", Toast.LENGTH_LONG).show();
	            		
	            	}
	            }
	        });
	      all.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            
	            	todoItems=sql.todayEvent();
	            	
	            	 
	                Intent i=new Intent(Event_list.this,Event_list.class);
	                i.putExtra("id", "0");
	                startActivity(i);
	            	
	            }
	        });
	      //onlong pressss
	     
	      
	      //end..............
		}catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.getMessage()+"all", Toast.LENGTH_LONG).show();
		}
	      
	}
//lllll
	/*
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
      if (v.getId()==R.id.mobile_list) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle("abc");
        String[] menuItems = {"add","delete","edit"};
        for (int i = 0; i<menuItems.length; i++) {
          menu.add(Menu.NONE, i, i, menuItems[i]);
        }
      }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      Toast.makeText(getApplicationContext(), item.getItemId(), Toast.LENGTH_LONG).show();
      
      return true;
    }
    */
	//jjjjjjj
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
