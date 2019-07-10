package org.dao;

import org.model.Session;

public class SqlLiteDao extends Dao{
	private static SessionDao SessionDao=null;
	private static PersonDao personDao=null;
	private static TransactionDao transactionDao=null;
	static
	{
		/*SessionDao=new SessionDaoImpl();
		personDao=new PersonDaoImpl();
		transactionDao=new TransactionDaoImpl();*/
		SessionDao=new SqlliteSessionDaoImpl();
		personDao=new SqllitePersonDaoImpl();
		transactionDao=new SqlliteTransDaoImpl();
	}
	@Override
	public SessionDao getSessionDao() {
		// TODO Auto-generated method stub
		return SessionDao;
	}

	@Override
	public PersonDao getPersonDao() {
		// TODO Auto-generated method stub
		return personDao;
	}

	@Override
	public TransactionDao getTransctionDao() {
		// TODO Auto-generated method stub
		return transactionDao;
	}
	
}
