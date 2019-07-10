package org.screens;

import java.util.ArrayList;
import java.util.List;

import org.adapters.SessionsAdapter;
import org.aniket.R;
import org.aniket.R.id;
import org.aniket.R.layout;
import org.aniket.R.menu;
import org.dao.Dao;
import org.dao.PersonDao;
import org.dao.SessionDao;
import org.model.Person;
import org.model.Session;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AllSessions extends MasterActivity implements OnItemClickListener,OnClickListener{

		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_sessions);
		ListView sessions=(ListView) findViewById(R.id.sessions);
		TextView emptyText=(TextView) findViewById(R.id.emptyText);
		
		SessionsAdapter sessionsAdapter=new SessionsAdapter();
		sessions.setAdapter(sessionsAdapter);
		sessions.setOnItemClickListener(this);
		sessions.setEmptyView(emptyText);
		
//		Button backToHomeScreen=(Button) findViewById(R.id.backToHomeScreen);
//		backToHomeScreen.setOnClickListener(this);
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		getSharedPreferences("PicnicExpenceTracker", MODE_PRIVATE).edit().putInt("sessionId",(int) id).commit();
		Intent i=new Intent(this,SessionScreen.class);				
		startActivity(i);
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
				
//			case R.id.backToHomeScreen:				
//				finish();
//				break;
				
		}
		
	}
}
