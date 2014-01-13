
package com.example.VolleyPost;  
//package com.yoursite.helloworld;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;


import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
// import everything you need
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    // Visual elements
    Button startButton;
    Button archiveButton;
    Button homePlusButton;
    Button homeMinusButton;
    Button awayPlusButton;
    Button awayMinusButton;
    
    RadioButton gm1Button;
    RadioButton gm2Button;
    RadioButton gm3Button;
    RadioButton gm4Button;
    RadioButton gm5Button;
    
    ImageView alert;
    
    EditText msgTextField;
    EditText opponentTextField;
    
    TextView homeScore;
    TextView awayScore;
    
    // initialize variables
    int homescore = 0;
    int awayscore =0;
    int gamenum = 0;

    /** Called when the activity is first created. */
    @SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
    	   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
           .detectDiskReads()
           .detectDiskWrites()
           .detectNetwork()   // or .detectAll() for all detectable problems
           .penaltyLog()
           .build());
    	     	   
    	   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	
        super.onCreate(savedInstanceState);
        // load the layout
        setContentView(R.layout.activity_main);        

        // make opponent text field object
        opponentTextField = (EditText) findViewById(R.id.opponentTextField);
        // make button objects
        startButton = (Button) findViewById(R.id.startButton);
        archiveButton = (Button) findViewById(R.id.archiveButton);
        homePlusButton = (Button) findViewById(R.id.homePlusButton);
        homeMinusButton = (Button) findViewById(R.id.homeMinusButton);
        awayPlusButton = (Button) findViewById(R.id.awayPlusButton);
        awayMinusButton = (Button) findViewById(R.id.awayMinusButton);
        gm1Button = (RadioButton) findViewById(R.id.gm1Button);
        gm2Button = (RadioButton) findViewById(R.id.gm2Button);
        gm3Button = (RadioButton) findViewById(R.id.gm3Button);
        gm4Button = (RadioButton) findViewById(R.id.gm4Button);
        gm5Button = (RadioButton) findViewById(R.id.gm5Button);
        alert = (ImageView) findViewById(R.id.imageView1);
        // make textview object
        homeScore = (TextView) findViewById(R.id.homeScore);
        awayScore = (TextView) findViewById(R.id.awayScore);
        // activate OnClickListeners
        homePlusButton.setOnClickListener(this);
        homeMinusButton.setOnClickListener(this);
        awayPlusButton.setOnClickListener(this);
        awayMinusButton.setOnClickListener(this);
        gm1Button.setOnClickListener(this);
        gm2Button.setOnClickListener(this);
        gm3Button.setOnClickListener(this);
        gm4Button.setOnClickListener(this);
        gm5Button.setOnClickListener(this);
        gm1Button.setChecked(true);
        gamenum = 1;
    }

// START BUTTON PRESS
    public void start(View v)
    {
        // get the message from the message text box  
        String opponent = opponentTextField.getText().toString();
            
        homescore = 0;
        homeScore.setText(Integer.toString(homescore));
        awayscore = 0;
        awayScore.setText(Integer.toString(awayscore));

        
     
        // make sure the fields are not empty
        if (opponent.length()>0)
        {
            HttpParams params = new BasicHttpParams(); 
            HttpConnectionParams.setConnectionTimeout(params, 5000); 
            HttpConnectionParams.setSoTimeout(params, 10000);
        	
            HttpClient httpclient = new DefaultHttpClient(params);
	    //HttpPost httppost = new HttpPost("http://192.168.1.102/script.php");
            HttpPost httppost = new HttpPost("http://www.foosechek.org/script.php");
	    try {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
	        nameValuePairs.add(new BasicNameValuePair("id", "START"));
	        nameValuePairs.add(new BasicNameValuePair("opponent", opponent));
	        nameValuePairs.add(new BasicNameValuePair("game", Integer.toString(gamenum)));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        if (response.getStatusLine().getStatusCode() != 200)
	            alert.setVisibility(View.VISIBLE);
	        else
	    	    alert.setVisibility(View.INVISIBLE);
	       
	     } catch (ClientProtocolException e) {
	        e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     } 
        } else {
            // display message if text fields are empty
            Toast.makeText(getBaseContext(),"All field are required",Toast.LENGTH_SHORT).show();
        }
    }
           
    // this is the function that gets called when you click the button
    @SuppressLint("NewApi")


// ARCHIVE BUTTOM PRESS
    public void archive(View v)
    {   
        // get the message from the message text box  
        String opponent = opponentTextField.getText().toString();
        String game = Integer.toString(gamenum);
        
        homescore = 0;
        homeScore.setText(Integer.toString(homescore));
        awayscore = 0;
        awayScore.setText(Integer.toString(awayscore));
        gm1Button.setChecked(true);
        gm2Button.setChecked(false);
        gm3Button.setChecked(false);
        opponentTextField.setText("");
        gamenum =1; 
        
        HttpParams params = new BasicHttpParams(); 
       	HttpConnectionParams.setConnectionTimeout(params, 5000); 
       	HttpConnectionParams.setSoTimeout(params, 10000);
       	
       	HttpClient httpclient = new DefaultHttpClient(params);
   	//HttpPost httppost = new HttpPost("http://192.168.1.102/script.php");
       	HttpPost httppost = new HttpPost("http://www.foosechek.org/script.php");
     	try {
     	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
     	    nameValuePairs.add(new BasicNameValuePair("id", "ARCHIVE"));
     	    nameValuePairs.add(new BasicNameValuePair("archive", "archive"));
     	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
     	    HttpResponse response = httpclient.execute(httppost);
     	    if (response.getStatusLine().getStatusCode() != 200)
   	        alert.setVisibility(View.VISIBLE);
   	    else
   	    	alert.setVisibility(View.INVISIBLE);
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} 
    }
 
// Have to implement with the OnClickListner
// onClick is called when a view has been clicked.

    public void onClick(View v) { // Parameter v stands for the view that was clicked.  
        boolean update = false;
	// getId() returns this view's identifier
        switch(v.getId()){
            
            case R.id.homePlusButton: {
	        // setText() sets the string value of the TextView
	        homescore++;
	        update = true;
	        homeScore.setText(Integer.toString(homescore));
                break;
	    }
            case R.id.homeMinusButton: {
                if(homescore >= 1)
		    homescore--;
		update = true;:w
		homeScore.setText(Integer.toString(homescore));
                break;
	    }
            case R.id.awayPlusButton: {
		awayscore++;
		pdate = true;
		awayScore.setText(Integer.toString(awayscore));
                break;
            }
            case R.id.awayMinusButton: {
		if(awayscore >= 1)
		awayscore--;
		update = true;
		awayScore.setText(Integer.toString(awayscore));
                break;
	    }
            case R.id.gm1Button: {
		gamenum = 1;
                break;
	    }
            case R.id.gm2Button: {
		gamenum = 2;
                break;
            }
            case R.id.gm3Button: {
		gamenum = 3;
                break;
            }
            case R.id.gm4Button: {
		gamenum = 4;
                break;
            } 
            case R.id.gm5Button): {
		gamenum = 5;
                break;
	    }

        }
	// something has changed that is update worthy	
	if(update) {
	    HttpParams params = new BasicHttpParams(); 
            HttpConnectionParams.setConnectionTimeout(params, 5000); 
            HttpConnectionParams.setSoTimeout(params, 10000);
        	
            HttpClient httpclient = new DefaultHttpClient(params);
	    //HttpPost httppost = new HttpPost("http://192.168.1.102/script.php");
            HttpPost httppost = new HttpPost("http://www.foosechek.org/script.php");
	    try {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
	        nameValuePairs.add(new BasicNameValuePair("id", "SCORE"));
	        nameValuePairs.add(new BasicNameValuePair("game", Integer.toString(gamenum)));
	        nameValuePairs.add(new BasicNameValuePair("home", Integer.toString(homescore)));
	        nameValuePairs.add(new BasicNameValuePair("away", Integer.toString(awayscore)));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        if (response.getStatusLine().getStatusCode() != 200)
	    	    alert.setVisibility(View.VISIBLE);
	        else
	    	    alert.setVisibility(View.INVISIBLE);
	       
	    	   
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
    }
}
