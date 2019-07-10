package org.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class PicnicLogic {
	List<Transaction> transactions;
	List<Person> persons;	
	public PicnicLogic(List<Transaction> transactions,List<Person> persons) 
	{
		this.transactions=transactions;
		this.persons=persons;
	}
	
	public List<Result> calculateResult()
	{
		//calculate net contribution by each person
		persons=calculatePersonWiseContribution(transactions,persons);			
		
		//calculate total amount spend.
		float totalAmt=calculateTotalAmt(persons);
		
		//calculate total persons.
		int noOfPersons =calculateNoOfPersons(persons);
		
		//calculate amount to be contributed by each person (avg amt).
		float avgAmt=totalAmt/noOfPersons;
		
		//remove person who calculated = avg amt , since it is idle person hence doesnt participated in transfer money operation
		List <Person> activePersons=removePerson(persons,avgAmt);
		
		//Seperate list of persons who contributed > avg amt (credit list) and <avg amt (debit list).		
		Map <String, List<Person>> creditDebitList=seperateCreditDebitPersonList(activePersons,avgAmt);
		List<Person> creditList=creditDebitList.get("CREDIT");
		List<Person> debitList=creditDebitList.get("DEBIT");
				
		//transfer money from debit list to credit list of persons and store the result.
		//return the result.
		return calculate(creditList,debitList);
		
		
		
		
		
		
				
	
	}
	private List<Person> calculatePersonWiseContribution(List<Transaction> transactions,List<Person> persons) 
	{		
		
		Map<String, Float> map=new HashMap<String, Float>();
		float amtContributed=0;
		for(Transaction trn:transactions)
		{
			amtContributed=trn.getAmtSpend();
			if(map.containsKey(trn.getPerson().getName()))
			{
				amtContributed+=map.get(trn.getPerson().getName());
				map.put(trn.getPerson().getName(), amtContributed);
			}
			else
			{
				map.put(trn.getPerson().getName(), amtContributed);
			}
					
		}
		for(Person person:persons)
		{
			if(map.containsKey(person.getName()))
			{
				person.setAmtContributed(map.get(person.getName()));
			}
			else
			{
				person.setAmtContributed(0);
			}
		}
		
		return persons;
	}
	private List<Result> calculate(List<Person> creditList,List<Person> debitList) 
	{
		List <Result> result=new ArrayList<>();
		for(int i=0;i<creditList.size();i++)
		{
			Person creditPerson=creditList.get(i);
			Person debitPerson=debitList.get(i);
			
			if(debitPerson.getPreviousAmt() > creditPerson.getPreviousAmt())
			{
				debitPerson.setCurrentAmt(debitPerson.getPreviousAmt()-creditPerson.getPreviousAmt());
				creditPerson.setCurrentAmt(0);	
				debitList.add(new Person(debitPerson.getName(),debitPerson.getAmtContributed(),debitPerson.getCurrentAmt(),debitPerson.getCurrentAmt()));
				result.add(new Result(debitPerson.getName(),creditPerson.getName(),creditPerson.getPreviousAmt()));
			}
			else if(debitPerson.getPreviousAmt() == creditPerson.getPreviousAmt())
			{
				debitPerson.setCurrentAmt(0);
				creditPerson.setCurrentAmt(0);
				result.add(new Result(debitPerson.getName(),creditPerson.getName(),creditPerson.getPreviousAmt()));
			}
			else
			{
				debitPerson.setCurrentAmt(0);
				creditPerson.setCurrentAmt(creditPerson.getPreviousAmt()-debitPerson.getPreviousAmt());
				creditList.add(new Person(creditPerson.getName(),creditPerson.getAmtContributed(),creditPerson.getCurrentAmt(),creditPerson.getCurrentAmt()));
				result.add(new Result(debitPerson.getName(),creditPerson.getName(),debitPerson.getPreviousAmt()));
			}
			
			
		}
		return result;
	}

	private List<Person> removePerson(List<Person> persons, float avgAmt) 
	{	
		List<Person> activePersons=new ArrayList<>();
		for(int i=0;i<persons.size();i++)
		{
			if(persons.get(i).getAmtContributed()!=avgAmt)
				activePersons.add(persons.get(i));
		}
		return activePersons;
	}

	private int calculateNoOfPersons(List<Person> persons) {
		return persons.size();
	}

	private int calculateTotalAmt(List<Person> persons) {
		int totalAmt=0;
		for(Person person:persons){
			totalAmt+=person.getAmtContributed();
		}
		return totalAmt;
	}

	private Map<String, List<Person>> seperateCreditDebitPersonList(List<Person> persons,float avgAmt) {
		
		Map <String, List<Person>> CreditDebitPersonList= new HashMap<String, List<Person>>();
		
		List<Person> creditList=new ArrayList<>();
		List<Person> debitList=new ArrayList<>();
		
		for(Person person:persons)
		{
			if(person.getAmtContributed() > avgAmt)
			{
				person.setPreviousAmt(person.getAmtContributed()-avgAmt);
				person.setCurrentAmt(person.getPreviousAmt());
				creditList.add(person);
			}
			else
			{
				person.setPreviousAmt(avgAmt-person.getAmtContributed());
				person.setCurrentAmt(person.getPreviousAmt());
				debitList.add(person);
			}
		}
		CreditDebitPersonList.put("CREDIT", creditList);
		CreditDebitPersonList.put("DEBIT", debitList);
		return CreditDebitPersonList;
	}

	
}
