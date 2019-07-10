package org.adapters;

import java.util.ArrayList;
import java.util.List;

import org.aniket.R;
import org.dao.Dao;
import org.model.Session;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SessionsAdapter extends BaseAdapter{
	List<Session> sessions=Dao.getInstance().getSessionDao().getAllSessions();
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sessions.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sessions.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return sessions.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.sessons_item, parent,false);
		}

		Session session=sessions.get(position);
		
		TextView sessionTextView = (TextView)
				convertView.findViewById(R.id.session_view);
		sessionTextView .setText(session.getName());
		
		
		return convertView;
	}

}
