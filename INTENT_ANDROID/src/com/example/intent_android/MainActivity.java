package com.example.intent_android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	TextView resultText;
    EditText text1;
    Button   btnDial, btnActionViewMap, btnViewContact, btnEditContact, btnCalculator;
    Intent myActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnDial = (Button) findViewById(R.id.ACTION_DIAL);
        btnActionViewMap = (Button) findViewById(R.id.VIEW_MAP);
        btnViewContact = (Button) findViewById(R.id.VIEW_CONTACT);
        btnEditContact = (Button) findViewById(R.id.EDIT_CONTACT);
        btnCalculator = (Button) findViewById(R.id.btnCalculator);
        resultText = (TextView) findViewById(R.id.resultText);
        
        btnDial.setOnClickListener(new OnClickListener() {
        	@Override
		  	public void onClick(View arg0) {
        		myActivity = new Intent (Intent.ACTION_DIAL, Uri.parse( "tel:555-1234"));
        		startActivity(myActivity);
        	}
        });
        btnActionViewMap.setOnClickListener(new OnClickListener() {
        	@Override
		  	public void onClick(View arg0) {
    			myActivity = new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?0?q=1860+east+18th+street+cleveland+oh"));
    			startActivity(myActivity);
        	}
        });
        btnViewContact.setOnClickListener(new OnClickListener() {
        	@Override
		  	public void onClick(View arg0) {
        		String myData = "content://contacts/people/1";
        		myActivity = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));
        		startActivity(myActivity);
        	}
        });
        btnEditContact.setOnClickListener(new OnClickListener() {
        	@Override
		  	public void onClick(View arg0) {
        		String myData = "content://contacts/people/1";
        		myActivity = new Intent(Intent.ACTION_EDIT, Uri.parse(myData));
        		startActivity(myActivity);
        	}
        });
        btnCalculator.setOnClickListener(new OnClickListener() {
        	@Override
		  	public void onClick(View arg0) {
        		 Bundle myData = new Bundle();
        		 myActivity = new Intent (MainActivity.this,
                         Activity2.class);
        		 // add <key,value> data items to the container
        		 myData.putInt("x", 4);
        		 // attach the container to the intent
        		 myActivity.putExtras(myData);

        		 // call Activity2, tell your local listener to wait response
        		 startActivityForResult(myActivity, 101);
        	}
        });
    }


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
    @Override
    protected void onActivityResult(int requestCode, 
                                    int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	
	    try{
	       if ((requestCode == 101 ) && (resultCode == Activity.RESULT_OK)){
	       Bundle myResults = data.getExtras();
	       int result = myResults.getInt("result");
	       resultText.setText("X x 2 = " + result);
	       }
	    }
	    catch (Exception e) {
	    	resultText.setText("Errors");
	    }
    }//onActivityResult

}
