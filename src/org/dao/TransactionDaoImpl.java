package org.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.model.Person;
import org.model.Transaction;

public class TransactionDaoImpl implements TransactionDao{

	List<Transaction> transactions=new ArrayList<>();
	public TransactionDaoImpl() {
	/*transactions.add(new Transaction(1,0,new Person(0,"Aniket"),4,Calendar.getInstance(),"travel.."));
	transactions.add(new Transaction(2,0,new Person(0,"Avinash"),6,Calendar.getInstance(),"purchase"));
	transactions.add(new Transaction(3,0,new Person(0,"Swapneel"),16,Calendar.getInstance(),"food"));
	transactions.add(new Transaction(4,0,new Person(0,"Vivek"),8,Calendar.getInstance(),"purchase"));
	transactions.add(new Transaction(5,0,new Person(0,"Avinash"),6,Calendar.getInstance(),"water"));
	transactions.add(new Transaction(6,0,new Person(0,"Swapneel"),10,Calendar.getInstance(),"ticket"));
	transactions.add(new Transaction(7,0,new Person(0,"Sagar"),10,Calendar.getInstance(),"travelling"));*/
	}
	@Override
	public int insertTransaction(Transaction transaction) {
		int size = transactions.size()+1;		
		transaction.setId(size);
		transactions.add(transaction);	
		throw new RuntimeException("Method not implemented properly");
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		for(Transaction trans:transactions)
		{
			if(trans.getId()==transaction.getId())
			{
				transactions.remove(trans);
				transactions.add(transaction);				
				break;
			}
		}
		
	}

	@Override
	public List<Transaction> getTransactionsBySessions(int sessionId) {
		List<Transaction> transBySession=new ArrayList<>();
		for(Transaction transaction:transactions)
		{
			if(transaction.getSessionId()==sessionId)
			{
				transBySession.add(transaction);
			}
		}
		return transBySession;
	}

	@Override
	public Transaction getTransactionById(int transId) {

		for(Transaction transaction:transactions)
		{
			if(transaction.getId()==transId)
			{
				return transaction;
			}
		}
		return null;
	}

	@Override
	public void deleteTransaction(int transactionId) 
	{
		for(Transaction transaction:transactions)
		{
			if(transaction.getId()==transactionId)
			{
				transactions.remove(transaction);
			}
		}
		
	}

}
