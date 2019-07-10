package org.screens;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.aniket.R;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MasterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.master_activity);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.master, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 //Handle action bar item clicks here. The action bar will
		 //automatically handle clicks on the exit button, so long
		 //as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
	    // Respond to the action bar's Exit button
	    case R.id.action_exit:
	    	
	      //hit the server to call the service to show the values from database

			try {

				// URLEncode user defined data

				String loginValue = URLEncoder.encode("transaction|transactionId,transactionAmount|1,200", "UTF-8");
//				String fnameValue = URLEncoder.encode(fname.getText()
//						.toString(), "UTF-8");
//				String emailValue = URLEncoder.encode(email.getText()
//						.toString(), "UTF-8");
//				String passValue = URLEncoder.encode(pass.getText().toString(),
//						"UTF-8");

				// Create http cliient object to send request to server

				HttpClient Client = new DefaultHttpClient();

				// Create URL string

				String URL = "http://androidexample.com/media/webservice/httpget.php?user="
						+ loginValue;
//						+ "&name="
//						+ fnameValue
//						+ "&email="
//						+ emailValue + "&pass=" + passValue;
				Log.d("PicnicFilter", URL+":"+URL);
				// Log.i("httpget", URL);

				String serverString = "";

				// Create Request to server and get response
				
				HttpPost httpget = new HttpPost(URL);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				serverString = Client.execute(httpget, responseHandler);

			} catch (Exception ex) {
				Log.d("PicnicFilter", ex.getMessage());
			}
             
          
	    	
	    	
	    	
	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
	 
				// set title
				alertDialogBuilder.setTitle("Picnic Expense Tracker");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Are you sure want to exit?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					  })
					.setNegativeButton("No",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}
					});
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
					break;
	    case android.R.id.home:
	    			finish();
				}
		
	    
	    return super.onOptionsItemSelected(item);

	}
}
