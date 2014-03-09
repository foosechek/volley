
package com.example.VolleyPost;  
//package com.yoursite.helloworld;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.http.client.ClientProtocolException;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
// import everything you need
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
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
    
    public List<NameValuePair> PostParams = new ArrayList<NameValuePair>();
    HttpPost httppost = new HttpPost("http://www.foosechek.org/ecp16lv/script.php");
    //HttpPost httppost = new HttpPost("http://192.168.1.139/var/www/volley/script.php");
    HttpParams params = new BasicHttpParams();
    
   
    

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
        msgTextField = (EditText) findViewById(R.id.msgTextField); 
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
        alert = (ImageView) findViewById(R.id.postStatus);
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
        if (opponent.length()>0) {
            PostParams.clear();
            PostParams.add(new BasicNameValuePair("id", "START"));
            PostParams.add(new BasicNameValuePair("opponent", opponent));
	        PostParams.add(new BasicNameValuePair("game", Integer.toString(gamenum)));
            postData();
        } else {
            // display message if text fields are empty
            Toast.makeText(getBaseContext(),"All field are required",Toast.LENGTH_SHORT).show();
        }
    }
          
    // START BUTTON PRESS
    public void post(View v)
    {
        // get the message from the message text box  
        String message = msgTextField.getText().toString();

	    PostParams.clear();
	    PostParams.add(new BasicNameValuePair("id", "MESSAGE"));
	    PostParams.add(new BasicNameValuePair("message", message));
        postData();
    }
   
           
    
    // this is the function that gets called when you click the button
    @SuppressLint("NewApi")


    // ARCHIVE BUTTOM PRESS
    public void archive(View v)
    {   
        homescore = 0;
        homeScore.setText(Integer.toString(homescore));
        awayscore = 0;
        awayScore.setText(Integer.toString(awayscore));
        gm1Button.setChecked(true);
        gm2Button.setChecked(false);
        gm3Button.setChecked(false);
        opponentTextField.setText("");
        gamenum =1; 
        PostParams.clear();
     	PostParams.add(new BasicNameValuePair("id", "ARCHIVE"));
     	PostParams.add(new BasicNameValuePair("archive", "archive"));
        postData();
    }
 
    // Have to implement with the OnClickListner
    // onClick is called when a view has been clicked.

    public void onClick(View v) { // Parameter v stands for the view that was clicked.  
        boolean update = false;
        switch(v.getId()){
            case R.id.homePlusButton: {
	            homescore++;
	            update = true;
	            homeScore.setText(Integer.toString(homescore));
                break;
	        }
            case R.id.homeMinusButton: {
                if(homescore >= 1)
		            homescore--;
		        update = true;
		        homeScore.setText(Integer.toString(homescore));
                break;
	        }
            case R.id.awayPlusButton: {
		        awayscore++;
		        update = true;
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
            case R.id.gm5Button: {
		        gamenum = 5;
                break;
	        }
            case R.id.button2: {
                update = true;
        		break;
            }
        }
	    // something has changed that is update worthy	
	    if(update) {
	        PostParams.clear();
	        PostParams.add(new BasicNameValuePair("id", "SCORE"));
	        PostParams.add(new BasicNameValuePair("game", Integer.toString(gamenum)));
	        PostParams.add(new BasicNameValuePair("home", Integer.toString(homescore)));
	        PostParams.add(new BasicNameValuePair("away", Integer.toString(awayscore)));
            postData();
	    }
    }
    
    public void postData(){
        try {
            httppost.setEntity(new UrlEncodedFormEntity(PostParams));

            ConnManagerParams.setTimeout(params, 5000);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
         	HttpProtocolParams.setContentCharset (params, HTTP.UTF_8);
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000); 
            
            HttpClient httpclient = new DefaultHttpClient();

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            int code = response.getStatusLine().getStatusCode();
            
            Toast.makeText(this, "code "+code, Toast.LENGTH_SHORT).show();


        } catch(ConnectTimeoutException e){
            Log.e("Timeout Exception: ", e.toString());
        } catch(SocketTimeoutException ste){    
            Log.e("Timeout Exception: ", ste.toString());
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection "+e.toString());
        }
    }
}

