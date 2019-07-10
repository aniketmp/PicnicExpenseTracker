package org.adapters;

import java.util.List;

import org.aniket.R;
import org.dao.Dao;
import org.model.Session;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class DeleteSessionAdapter extends BaseAdapter {
	
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
			convertView = inflater.inflate(R.layout.delete_session_item, parent,false);
		}

		final Session session=sessions.get(position);
		
		TextView sessionTextView = (TextView) convertView.findViewById(R.id.delete_session_view);
		sessionTextView .setText(session.getName());
		
		CheckBox checkBox=(CheckBox) convertView.findViewById(R.id.checkbox);
		checkBox.setChecked(session.checked);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {		
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {				
				session.checked=isChecked;
				Log.d("PicnicFilter", "isChecked:"+isChecked);
			}
		});
		
		
		return convertView;
	}

	


}
