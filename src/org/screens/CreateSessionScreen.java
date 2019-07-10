package org.screens;

import org.aniket.R;
import org.dao.Dao;
import org.dao.SessionDao;
import org.dao.SessionDaoImpl;
import org.model.Session;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateSessionScreen extends MasterActivity implements OnClickListener{
	private static int uniqueId=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_session_screen);
		Button saveSessionName=(Button) findViewById(R.id.saveSessionName);
		Button cancelSessionName=(Button) findViewById(R.id.cancelSessionName);
		saveSessionName.setOnClickListener(this);
		cancelSessionName.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {		
		super.onStart();
		getActionBar().setTitle("Create New Session!");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.saveSessionName:							
				SessionDao sessionDao=Dao.getInstance().getSessionDao();		
				EditText sessionNameView=(EditText) findViewById(R.id.sessionName);
				int sessionId=sessionDao.insertSession(new Session(sessionNameView.getText().toString()));				
				
				Toast.makeText(this, "Session Saved Successfully....\nNow you can start filling person and transaction details", Toast.LENGTH_LONG).show();
				getSharedPreferences("PicnicExpenceTracker", MODE_PRIVATE).edit().putInt("sessionId",sessionId).commit();
				getSharedPreferences("PicnicExpenceTracker", MODE_PRIVATE).edit().putString("sessionName",sessionNameView.getText().toString()).commit();
				Intent i=new Intent(this,SessionScreen.class);				
				startActivity(i);				
				break;
				
			case R.id.cancelSessionName:				
				finish();
				break;
		}
		
	}
	
	
}
