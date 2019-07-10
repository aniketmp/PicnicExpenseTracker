package org.screens;

import java.util.List;

import org.aniket.R;
import org.dao.Dao;
import org.dao.PersonDao;
import org.dao.TransactionDao;
import org.model.Person;
import org.model.PicnicLogic;
import org.model.Result;
import org.model.Transaction;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultScreen extends MasterActivity implements OnClickListener
{
	private static final PersonDao persoDao=Dao.getInstance().getPersonDao();
	private static final TransactionDao transactionDao=Dao.getInstance().getTransctionDao();
	private List<Result> calculateResult;
	private List<Transaction> transactions;
	private List<Person> persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_screen);
		
		Button cancel=(Button) findViewById(R.id.calcelResult);
		cancel.setOnClickListener(this);
		int sessionId=getIntent().getIntExtra("sessionId", 1);
		String sesionTitle=getIntent().getStringExtra("sessionTitle");
				
		transactions=transactionDao.getTransactionsBySessions(sessionId);
		persons=persoDao.getPersonsBySession(sessionId);
		PicnicLogic picnicLogic=new PicnicLogic(transactions, persons);
		calculateResult = picnicLogic.calculateResult();		
		
		TextView resultTitle=(TextView) findViewById(R.id.resultTitle);
		resultTitle.setText(sesionTitle);
		
		ListView resultList=(ListView) findViewById(R.id.resultList);
//		TextView emptyView=new TextView(this);
//		emptyView.setText("No result to display");
//		resultList.setEmptyView(emptyView);
		ArrayAdapter<Result> resultAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, calculateResult);
		resultList.setAdapter(resultAdapter);
		
		
	}

	@Override
	public void onClick(View v) {
		 switch (v.getId()){
	        case R.id.calcelResult:	        	
	        	finish();
	            break;

	        case R.id.printToPDF:	            
	            break;
	        }
		
	}
}
