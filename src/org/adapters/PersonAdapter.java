package org.adapters;

import java.util.List;

import org.aniket.R;
import org.model.Person;
import org.model.Session;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {

	List<Person> persons = null;

	public PersonAdapter(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return persons.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.person_row, parent, false);
		}

		Person person = persons.get(position);

		TextView personTextView = (TextView) convertView.findViewById(R.id.personText);
		personTextView.setText(person.getName());
		return convertView;

	}

}
