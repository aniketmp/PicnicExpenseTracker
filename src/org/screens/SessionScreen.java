package org.screens;

import java.util.List;

import org.aniket.R;
import org.dao.Dao;
import org.dao.PersonDao;
import org.dao.TransactionDao;
import org.model.Person;
import org.model.Transaction;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SessionScreen extends MasterActivity implements OnClickListener {

	private int sessionId;
	private TransactionDao transDao=Dao.getInstance().getTransctionDao();
    private PersonDao personDao=Dao.getInstance().getPersonDao();
	TextView text_transDetails;
    String message_transDetails;
    TextView text_personDetails;
    String message_personDetails;
    TextView text_resultDetails;
    String message_resultDetails;
    TextView text_sessionDetails;
    String message_sessionDetails;
    TextView text_sessionsDetail;
    String message_sessionsDetail;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.session_screen);

		Button button_personDetails = (Button) findViewById(R.id.button_personDetails);
		button_personDetails.setOnClickListener(this);

		Button button_resultDetails = (Button) findViewById(R.id.button_resultDetails);
		button_resultDetails.setOnClickListener(this);

		Button button_sessionDetails = (Button) findViewById(R.id.button_sessionDetails);
		button_sessionDetails.setOnClickListener(this);

		Button button_sessionsDetail = (Button) findViewById(R.id.button_sessionsDetail);
		button_sessionsDetail.setOnClickListener(this);

		Button button_transDetails = (Button) findViewById(R.id.button_transDetails);
		button_transDetails.setOnClickListener(this);

        text_transDetails = (TextView) findViewById(R.id.text_transDetails);
        message_transDetails=text_transDetails.getText().toString();

        text_personDetails = (TextView) findViewById(R.id.text_personDetails);
        message_personDetails=text_personDetails.getText().toString();

        text_resultDetails = (TextView) findViewById(R.id.text_resultDetails);
        message_resultDetails=text_resultDetails.getText().toString();

        text_sessionDetails = (TextView) findViewById(R.id.text_sessionDetails);
        message_sessionDetails=text_sessionDetails.getText().toString();

        text_sessionsDetail = (TextView) findViewById(R.id.text_sessionsDetail);
        message_sessionsDetail=text_sessionsDetail.getText().toString();




    }

	@Override
	protected void onStart() {
		super.onStart();
		sessionId = getSharedPreferences("PicnicExpenceTracker", MODE_PRIVATE)
				.getInt("sessionId", 0);
		 List<Transaction> transList= transDao.getTransactionsBySessions(sessionId);
		 int amt=0;
		 for(Transaction trans:transList)
		 {
			 amt+=trans.getAmtSpend();
		 }
		 text_transDetails.setText(message_transDetails+""+amt);

        List<Person> personList= personDao.getPersonsBySession(sessionId);
        text_personDetails.setText(message_personDetails+""+personList.size());
        text_resultDetails.setText(message_resultDetails+""+ (personList.size()==0? 0 : amt/personList.size()));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_transDetails:
			Intent i = new Intent(this, AllTransactionsScreen.class);
			startActivity(i);
			break;


		case R.id.button_personDetails:
			i = new Intent(this, AllPersonScreen.class);
			startActivity(i);
			break;
			
		case R.id.button_resultDetails:
			i = new Intent(this, ResultScreen.class);
			i.putExtra("sessionId", sessionId);
			i.putExtra("sessionTitle", "Test title");
			startActivity(i);
			break;
			
		case R.id.button_sessionDetails:
			break;
			
		case R.id.button_sessionsDetail:
			i=new Intent(this,AllSessions.class);
			startActivity(i);
			break;

		}

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		onBackPressed();
		return true;
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent=new Intent(this,HomeScreen.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
