// Activity2. This subactivity receives a bundle of data, performs some work on the data and, 
// returns results to Activity1.
package com.example.intent_android;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity {
    TextView label2;
    Button   btnCallActivity1;
    
 // Activity2 – cont…
    @Override
       public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.main2);
           //bind UI variables to Java code
           label2 = (TextView)findViewById(R.id.txDataReceived);
           btnCallActivity1 = (Button)findViewById(R.id.btnDone);

           //create a local Intent handler – we have been called!
           Intent myLocalIntent = getIntent();

           //grab the data package with all the pieces sent to us
           Bundle myBundle = myLocalIntent.getExtras();

           //extract the individual data parts of the bundle 
           int x = myBundle.getInt("x");
           myBundle.putInt("result", 2 * x);
           label2.setText(label2.getText() + "\n X=" + x);
           myLocalIntent.putExtras(myBundle);

           setResult(Activity.RESULT_OK, myLocalIntent);
           btnCallActivity1.setOnClickListener(new OnClickListener() {
           	@Override
   		  	public void onClick(View arg0) {
           		finish();
           	}
           });
       }//onCreate());
}