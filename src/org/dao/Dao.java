package org.dao;

public abstract class Dao {
	private static Dao dao=null;
	abstract public SessionDao getSessionDao();
	abstract public PersonDao getPersonDao();
	abstract public TransactionDao getTransctionDao();
	public static Dao getInstance()
	{
		return dao;
	}
	static
	{
		dao=new SqlLiteDao();
	}
}
