package org.dao;

import java.util.List;

import org.model.Transaction;

import android.content.Context;

public class SqlliteTransDaoImpl implements TransactionDao{
	DatabaseHelper databaseHelper=null;
	public SqlliteTransDaoImpl() {
		databaseHelper=DatabaseHelper.getInstance();
	}
	@Override
	public int insertTransaction(Transaction transaction) {
		return databaseHelper.insertTransaction(transaction);
		
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		databaseHelper.updateTransaction(transaction);
		
	}

	@Override
	public List<Transaction> getTransactionsBySessions(int sessionId) {
		return databaseHelper.getTransactionsBySessions(sessionId);
		
	}

	@Override
	public Transaction getTransactionById(int transId) {
		
		return databaseHelper.getTransactionById(transId);
	}

	@Override
	public void deleteTransaction(int transactionId) {
		databaseHelper.deleteTransaction(transactionId);
		
	}

}
