package org.screens;

import java.util.ArrayList;
import java.util.List;

import org.adapters.DeleteSessionAdapter;
import org.adapters.SessionsAdapter;
import org.aniket.R;
import org.aniket.R.id;
import org.aniket.R.layout;
import org.aniket.R.menu;
import org.dao.Dao;
import org.dao.SessionDao;
import org.model.Session;


import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import android.widget.Button;

import android.widget.ListView;
import android.widget.Toast;

public class DeleteSessions extends MasterActivity implements OnItemClickListener,OnClickListener{

	
	private static final SessionDao sessionDao = Dao.getInstance().getSessionDao();
	List<Session> sessions=sessionDao.getAllSessions();
	DeleteSessionAdapter deleteSessionAdapter=null;
	List<Integer> deleteSessionIdList=new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_sessions);
		ListView sessions=(ListView) findViewById(R.id.sessions);
		TextView emptyText=(TextView) findViewById(R.id.emptyText);
		
		deleteSessionAdapter=new DeleteSessionAdapter();
		sessions.setAdapter(deleteSessionAdapter);
		sessions.setOnItemClickListener(this);
		sessions.setEmptyView(emptyText);
		
//		Button backToHomeScreen=(Button) findViewById(R.id.backToHomeScreen);
//		backToHomeScreen.setOnClickListener(this);
		
		Button deleteSessions=(Button) findViewById(R.id.delete);		
		deleteSessions.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_sessions, menu);
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub		
		Session session=sessionDao.getSessionById((int)id);
		if(session.checked)
			session.checked=false;
		else
			session.checked=true;
		Toast.makeText(this, session.getName()+" "+session.checked, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
							
			case R.id.delete:
				boolean deleted=false;
				for(Session session:sessions)
				{
					if(session.checked)
					{
						deleteSessionIdList.add(session.getId());
						deleted=true;
					}
				}
				Log.d("PicnicFilter",sessions.toString());
				for(int deleteSessionId:deleteSessionIdList)
				{
					sessionDao.deleteSession(deleteSessionId);
				}				
				if(deleted)
					Toast.makeText(this, "Session Deleted Successfully", Toast.LENGTH_SHORT).show();
				Log.d("PicnicFilter",sessions.toString());
				deleteSessionAdapter.notifyDataSetChanged();
				
				break;
				
		}
		
	}
}
