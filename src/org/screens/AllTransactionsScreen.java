package org.screens;

import java.util.List;

import org.adapters.TransactionAdapter;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AllTransactionsScreen extends MasterActivity implements
		OnClickListener, OnItemClickListener {
	private int sessionId;
	List<Transaction> transactions = null;
	private static final TransactionDao transactionDao = Dao.getInstance()
			.getTransctionDao();
	BaseAdapter transactionAdapter = null;
	private static final int ADD_TRANSACTION = 2;
	List<Person> persons = null;
	private String sessionName;
	TextView emptyView=null;
	private static final PersonDao persoDao = Dao.getInstance().getPersonDao();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_transactions);		

	}

	@Override
	protected void onStart() {
		super.onStart();
		sessionId = getSharedPreferences("PicnicExpenceTracker", MODE_PRIVATE)
				.getInt("sessionId", 0);
		sessionName = getSharedPreferences("PicnicExpenceTracker", MODE_PRIVATE)
				.getString("sessionName","Transaction Details");

		transactions = transactionDao.getTransactionsBySessions(sessionId);
		ListView transactionList = (ListView) findViewById(R.id.transList);
		
		emptyView=(TextView) findViewById(R.id.emptyText);		

		transactionAdapter = new TransactionAdapter(transactions);		
		transactionList.setAdapter(transactionAdapter);
		transactionList.setOnItemClickListener(this);
		transactionList.setEmptyView(emptyView);
		
		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent i=new Intent(this,UpdateTransactionScreen.class);
		i.putExtra("transactionId", transactions.get(position).getId());
		i.putExtra("personName", transactions.get(position).getPerson().getName());
		i.putExtra("traId", 11);
		startActivity(i);

	}

	@Override
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
			if (persoDao.getPersonsBySession(sessionId).size() == 0) {
				Toast.makeText(
						this,
						"Cannot add transaction before adding person....\nPlease add person first",
						Toast.LENGTH_LONG).show();
			} else {
				intent = new Intent(this, AddTransactionScreen.class);
				startActivityForResult(intent, ADD_TRANSACTION);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_TRANSACTION) {
			if (resultCode == RESULT_OK) {
				transactionAdapter.notifyDataSetChanged();
			}
		}
	}
}
