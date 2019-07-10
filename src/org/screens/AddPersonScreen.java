package org.screens;

import org.aniket.R;
import org.dao.Dao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPersonScreen extends MasterActivity implements OnClickListener
{
	private Button addPerson;
	private EditText personName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_person_screen);
		addPerson = (Button) findViewById(R.id.addPersonToList);
		Button cancel=(Button) findViewById(R.id.cancelPersonToList);
		addPerson.setOnClickListener(this);
		cancel.setOnClickListener(this);
		personName = (EditText) findViewById(R.id.personName);
		
	}	
	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.addPersonToList:				
				Intent intent = getIntent();
				intent.putExtra("personName", personName.getText().toString());
				this.setResult(RESULT_OK, intent);			
				finish();
				break;
				
			case R.id.cancelPersonToList:
				this.setResult(RESULT_CANCELED);
				finish();
				break;
		}
		
		
	}
}
