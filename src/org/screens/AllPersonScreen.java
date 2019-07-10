package org.screens;

import java.util.List;

import org.adapters.PersonAdapter;
import org.aniket.R;
import org.dao.Dao;
import org.dao.PersonDao;
import org.dao.TransactionDao;
import org.model.Person;
import org.model.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AllPersonScreen extends MasterActivity implements OnClickListener, OnItemClickListener {
	private int sessionId;
	List<Person> persons= null;
	private static final PersonDao persoDao = Dao.getInstance().getPersonDao();
	BaseAdapter personsAdapter = null;
	private static final int ADD_PERSON= 1;
	private static final int UPDATE_PERSON= 2;
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.all_persons);
}
@Override
	protected void onStart() {		
		super.onStart();
		sessionId = getSharedPreferences("PicnicExpenceTracker", MODE_PRIVATE)
				.getInt("sessionId", 0);
		
		ListView personList = (ListView) findViewById(R.id.personList);
		persons = persoDao.getPersonsBySession(sessionId);
		personsAdapter = new PersonAdapter(persons);
		personList.setAdapter(personsAdapter);
		personList.setOnItemClickListener(this);
		TextView emptyText=(TextView) findViewById(R.id.emptyText);
		personList.setEmptyView(emptyText);

	}
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu items for use in the action bar
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.add_action, menu);
	return super.onCreateOptionsMenu(menu);

}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	Intent intent;
	// Handle presses on the action bar items
	switch (item.getItemId()) {
	case R.id.action_add:
		intent = new Intent(this, AddPersonScreen.class);
		startActivityForResult(intent, ADD_PERSON);		
		return true;
	default:
		return super.onOptionsItemSelected(item);
	}
}
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {	
	Intent i=new Intent(this,UpdatePersonScreen.class);
	i.putExtra("personName", persons.get(position).getName());
	i.putExtra("personId", persons.get(position).getId());
	
	startActivityForResult(i, UPDATE_PERSON);
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	
}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == ADD_PERSON) {
		if (resultCode == RESULT_OK) {
			String personName = data.getStringExtra("personName");
			persoDao.insertPerson(new Person(sessionId, personName));
			personsAdapter.notifyDataSetChanged();
		}
	}
	if (requestCode == UPDATE_PERSON) {
		if (resultCode == RESULT_OK) {			
			personsAdapter.notifyDataSetChanged();
		}
	}
  }

}
