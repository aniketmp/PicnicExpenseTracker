package org.dao;

import java.util.List;

import org.model.Transaction;

public interface TransactionDao {
	public int insertTransaction(Transaction transaction);
	public void updateTransaction(Transaction transaction);
	public List<Transaction> getTransactionsBySessions(int sessionId);
	public Transaction getTransactionById(int transId);
	public void deleteTransaction(int transactionId);
}
