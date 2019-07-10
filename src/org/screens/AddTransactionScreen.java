package org.screens;

import java.util.Calendar;
import java.util.List;

import org.adapters.PersonAdapter;
import org.aniket.R;
import org.dao.Dao;
import org.dao.PersonDao;
import org.dao.TransactionDao;
import org.model.Person;
import org.model.Result;
import org.model.Session;
import org.model.Transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddTransactionScreen extends MasterActivity implements OnItemSelectedListener,OnClickListener
{
	private TransactionDao transactionDao = null;
	private Person person =null;
	private List<Person> persons=null;
	private int sessionId;
	private Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_transaction_screen);
		
		spinner = (Spinner) findViewById(R.id.personNameSpinner);	
	    spinner.setOnItemSelectedListener(this);
	    
	    Button addTrans=(Button) findViewById(R.id.addTrans);
	    addTrans.setOnClickListener(this);
	    
	    Button cancelTrans=(Button) findViewById(R.id.cancelTrans);
	    cancelTrans.setOnClickListener(this);
    }
	
	@Override
	protected void onStart() {	
		super.onStart();
		
		sessionId=getSharedPreferences("PicnicExpenceTracker",MODE_PRIVATE).getInt("sessionId", 0);
		
		transactionDao = Dao.getInstance().getTransctionDao();
		persons=Dao.getInstance().getPersonDao().getPersonsBySession(sessionId);
		String personArr[]=new String [persons.size()];
		for(int i=0;i<personArr.length;i++)
		{
			personArr[i]=persons.get(i).getName();
		}
		
		
//		PersonAdapter adapter=new PersonAdapter(persons);
		ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personArr);
		spinner.setAdapter(adapter);
		
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
			String personStr= (String) parent.getItemAtPosition(position);
			for(Person personObj:persons)
			{
				if(personObj.getName().equals(personStr))
				{
					person=personObj;
				}
			}

		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
		
	}
	@Override
	public void onClick(View v) {
		
		
		int sessionId=getSharedPreferences("PicnicExpenceTracker",MODE_PRIVATE).getInt("sessionId", 0);
		switch (v.getId())
		{			
			case R.id.addTrans:
				EditText amtSpend=(EditText) findViewById(R.id.amtSpend);
				EditText comments=(EditText) findViewById(R.id.comments);
				Transaction t=new Transaction(sessionId,person,Float.parseFloat(amtSpend.getText().toString()),Calendar.getInstance(),comments.getText().toString());
				transactionDao.insertTransaction(t);
				finish();
				Toast.makeText(this, "Transaction Added Successfully", Toast.LENGTH_SHORT).show();				
				break;
			case R.id.cancelTrans:
				finish();
				break;
		}
	}
	

}
