package org.screens;


import org.aniket.R;
import org.dao.Dao;
import org.dao.PersonDao;
import org.model.Person;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePersonScreen extends Activity implements OnClickListener{
	private EditText personNameView;
	private String oldName= null;
	private int personId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_person_screen);
		Button updatePerson = (Button) findViewById(R.id.updatePersonToList);
		Button delete=(Button) findViewById(R.id.deletePersonToList);
		Button cancel=(Button) findViewById(R.id.cancelUpdatePersonToList);
		
		updatePerson.setOnClickListener(this);
		cancel.setOnClickListener(this);
		delete.setOnClickListener(this);
		
		personNameView = (EditText) findViewById(R.id.updatePersonName);		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		oldName=getIntent().getStringExtra("personName");
		Bundle b = getIntent().getExtras();
		personId= b.getInt("personId");			
		personNameView.setText(oldName);
	}
	@Override
	public void onClick(View v) 
	{ 
		switch (v.getId())
		{
			case R.id.updatePersonToList:				
				Person p=Dao.getInstance().getPersonDao().getPersonById(personId);
				p.setName(personNameView.getText().toString());
				Dao.getInstance().getPersonDao().updatePerson(p);
				finish();
				break;
				
			case R.id.deletePersonToList:				

				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Are you sure want to delete this person?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();
				
				break;
				
			case R.id.cancelUpdatePersonToList:				
				finish();
				break;
		}		
	}
	
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	        	Dao.getInstance().getPersonDao().deletePerson(oldName);	        	
	        	finish();
	            break;

	        case DialogInterface.BUTTON_NEGATIVE:
	            //No button clicked
	            break;
	        }
	    }
	};

}
