package org.screens;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.aniket.R;
import org.aniket.R.id;
import org.aniket.R.layout;
import org.aniket.R.menu;
import org.model.GlobalContext;
import org.model.Person;
import org.model.PicnicLogic;
import org.model.Result;
import org.model.Transaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeScreen extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GlobalContext.setContext(this);
		setContentView(R.layout.activity_main);
		
		Button createSession=(Button) findViewById(R.id.createSession);
		Button loadSession=(Button) findViewById(R.id.loadSession);
		Button deleteSession=(Button) findViewById(R.id.deleteSession);
		
		createSession.setOnClickListener(this);
		loadSession.setOnClickListener(this);
		deleteSession.setOnClickListener(this);
	}

	

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.createSession:
			Intent intent=new Intent(this,CreateSessionScreen.class);
			startActivity(intent);			
			break;
			
		case R.id.loadSession:
				intent=new Intent(this,AllSessions.class);
				startActivity(intent);
			break;
			
		case R.id.deleteSession:
				intent=new Intent(this,DeleteSessions.class);
				startActivity(intent);
			break;
		
		}		
		
		
	}
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		moveTaskToBack(true);
//	    finish();
//	}
}
