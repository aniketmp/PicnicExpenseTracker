package org.screens;

import java.util.Calendar;
import java.util.List;

import org.adapters.PersonAdapter;
import org.aniket.R;
import org.dao.Dao;
import org.dao.PersonDao;
import org.dao.TransactionDao;
import org.model.Person;
import org.model.Transaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateTransactionScreen extends Activity implements OnClickListener ,OnItemSelectedListener{
	private int id;	
	private TransactionDao transactionDao = null;
	private List<Person> persons=null;
	private int sessionId;
	private Spinner spinner;
	Person person;
	private EditText amtSpend;
	private EditText comments;
	private int transId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_transaction_screen);
		
		spinner = (Spinner) findViewById(R.id.updatePersonNameSpinner);
		spinner.setOnItemSelectedListener(this);
				
		Button updateTrans=(Button) findViewById(R.id.updateTrans);
		updateTrans.setOnClickListener(this);
		
		Button cancelTrans=(Button) findViewById(R.id.cancelUpdatedTrans);
		cancelTrans.setOnClickListener(this);
		
		Button deleteTrans=(Button) findViewById(R.id.deleteTrans);
		deleteTrans.setOnClickListener(this);		
		
				
	}
	
	@Override
	protected void onStart() {	
		super.onStart();
		
		sessionId=getSharedPreferences("PicnicExpenceTracker",MODE_PRIVATE).getInt("sessionId", 0);
		String oldPersonName=getIntent().getStringExtra("personName");
		
		Bundle b = getIntent().getExtras();
		transId= b.getInt("transactionId");		
		
		transactionDao = Dao.getInstance().getTransctionDao();
		persons=Dao.getInstance().getPersonDao().getPersonsBySession(sessionId);
		Transaction trans=transactionDao.getTransactionById(transId);
		
		amtSpend = (EditText) findViewById(R.id.updateAmtSpend);
		comments = (EditText) findViewById(R.id.updateComments);
		amtSpend.setText(String.valueOf(trans.getAmtSpend()));
		comments.setText(String.valueOf(trans.getComment()));
		
		PersonAdapter adapter=new PersonAdapter(persons);
		int position=0;
		for(int i=0;i<adapter.getCount();i++)
		{			
			Person person=(Person) adapter.getItem(i);
			String personName = person.getName();
			if(personName.equals(oldPersonName))
			{
				position=i;
				break;
			}
		
		}
		
		spinner.setAdapter(adapter);		
		spinner.setSelection(position);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId())
		{			
			case R.id.updateTrans:				
				Transaction t=new Transaction(transId,sessionId,person,Float.parseFloat(amtSpend.getText().toString()),Calendar.getInstance(),comments.getText().toString());				
				transactionDao.updateTransaction(t);
				finish();
				Toast.makeText(this, "Transaction Updated Successfully", Toast.LENGTH_SHORT).show();				
				break;
			case R.id.cancelUpdatedTrans:
				finish();
				break;
			case R.id.deleteTrans:
				transactionDao.deleteTransaction(transId);
				Toast.makeText(this, "Transaction Deleted Successfully", Toast.LENGTH_SHORT).show();
				finish();
				break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		person = (Person) parent.getItemAtPosition(position);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
