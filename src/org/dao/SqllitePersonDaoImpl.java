package org.dao;

import java.util.List;

import org.model.Person;

import android.content.Context;

public class SqllitePersonDaoImpl implements PersonDao{

	DatabaseHelper databaseHelper;
	public SqllitePersonDaoImpl() {
		databaseHelper=DatabaseHelper.getInstance();
	}
	@Override
	public void insertPerson(Person person) {	
		databaseHelper.insertPerson(person);
	}

	@Override
	public void updatePerson(Person person) {
		databaseHelper.updatePerson(person);
		
	}

	@Override
	public void updatePersonByName(String oldName, String newName) {
		databaseHelper.updatePersonByName(oldName, newName);
		
	}

	@Override
	public List<Person> getPersonsBySession(int sessionId) {
		return databaseHelper.getPersonsBySession(sessionId);
		
	}

	@Override
	public Person getPersonByName(String personName) {
		
		return databaseHelper.getPersonByName(personName);
	}

	@Override
	public void deletePerson(String personName) {
		databaseHelper.deletePerson(personName);
		
	}
	@Override
	public Person getPersonById(int personId) {
		return databaseHelper.getPersonById(personId); 
		
	}

}
