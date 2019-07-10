package org.dao;

import java.util.List;

import org.model.Person;

public interface PersonDao {
	public void insertPerson(Person person);
	public void updatePerson(Person person);
	public void updatePersonByName(String oldName,String newName);
	public List<Person> getPersonsBySession(int sessionId);
	public Person getPersonByName(String personName);
	public Person getPersonById(int personId);
	public void deletePerson(String personName);
}
