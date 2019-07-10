package org.dao;

import java.util.ArrayList;
import java.util.List;

import org.model.Person;
import org.model.Session;

public class PersonDaoImpl implements PersonDao{
	List<Person> persons=new ArrayList<>();
	
	public PersonDaoImpl() {
//		persons.add(new Person(0,"Aniket"));
//		persons.add(new Person(0,"Swapneel"));
//		persons.add(new Person(0,"Avinash"));
//		persons.add(new Person(0,"Mayur"));
//		persons.add(new Person(0,"Sagar"));
//		persons.add(new Person(0,"Vivek"));
		
	}
	@Override
	public void insertPerson(Person person) {
		persons.add(person);		
	}

	@Override
	public void updatePerson(Person person) {
		for(Person p:persons)
		{
			if(p.getName().equals(person.getName()))
			{
				p=person;
				return;
			}
		}
		
	}

	@Override
	public List<Person> getPersonsBySession(int sessionId) {
		List<Person> personsBySession=new ArrayList<>();
		for(Person p:persons)
		{
			if(p.getSessionId()==sessionId)
			{
				personsBySession.add(p);
			}
		}
		return personsBySession;
	}

	@Override
	public Person getPersonByName(String name) {
		Person p1=null;
		for(Person p2:persons)
		{
			if(p2.getName().equals(name))
			{
				p1=p2;
			}
		}
		return p1;
	}

	@Override
	public void deletePerson(String personName) 
	{
		for(int i=0;i< persons.size(); i++)
		{
			if(persons.get(i).getName().equals(personName))
			{
				persons.remove(i);
				return;
			}
		}
		
	}
	@Override
	public void updatePersonByName(String oldName, String newName) {
		for(Person p:persons)
		{
			if(p.getName().equals(oldName))
			{
				p.setName(newName);
			}
		}
		
	}
	@Override
	public Person getPersonById(int personId) {

		return null;
	}

}
