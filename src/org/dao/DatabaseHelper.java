package org.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.model.GlobalContext;
import org.model.Person;
import org.model.Session;
import org.model.Transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.webkit.WebView.FindListener;

public class DatabaseHelper implements TransactionDao, PersonDao, SessionDao
{
	private static String PERSON_NAME="PERSON_NAME";
	private static String PERSON_ID="PERSON_ID";
	private static String SESSION_ID="SESSION_ID"; 
	private static String AMT_CONTRIBUTED="AMT_CONTRIBUTED"; 
	private static String PREVIOUS_AMT="PREVIOUS_AMT"; 
	private static String CURRENT_AMT="CURRENT_AMT"; 
	private static String TRANS_ID="TRANS_ID"; 
	private static String AMT_SPEND="AMT_SPEND"; 
	private static String TRANS_TIME="TRANS_TIME"; 
	private static String COMMENT="COMMENT"; 
	private static String SESSION_NAME="SESSION_NAME";
	private static String PERSON_TABLE="PERSONS";
	private static String TRANSACTION_TABLE="TRANSACTIONS";
	private static String SESSION_TABLE="SESSIONS";

	
	//All of the constants for referencing the database internal values.
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PICKNIC_EXPENSE_TRACKER";	
	
	//Store variables for the OpenHelper and the database it opens
	private PicExpTrckOpenHelper openHelper;
	private SQLiteDatabase database;
	
	private static DatabaseHelper dbHelper=null;
	
	private DatabaseHelper(Context context)
	{
		Log.d("PicnicFilter","context:"+context);
		openHelper = new PicExpTrckOpenHelper(context);
		Log.d("PicnicFilter","openHelper:"+openHelper);
		//Get the writable database from the open helper.
		database = openHelper.getWritableDatabase();
		Log.d("PicnicFilter","database:"+database);
		
	}	
	public static DatabaseHelper getInstance()
	{
		if(dbHelper==null)
			return new DatabaseHelper(GlobalContext.getContext());
		else
			return dbHelper;					
	}
	class PicExpTrckOpenHelper extends SQLiteOpenHelper 
	{

		public PicExpTrckOpenHelper(Context context) {
			// Pass the name of the database and version no  to super			
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.d("PicnicFilter","DATABASE_NAME:"+DATABASE_NAME+"  DATABASE_VERSION:"+DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d("PicnicFilter","On create method");
			//Create your tables in here
			//Call execSQL on the database and Pass in the SQL statament to create the timerecords table.
			try
			{				
				db.execSQL(
						"CREATE TABLE "+SESSION_TABLE+"( "
								+ SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "								
								+ SESSION_NAME + " TEXT UNIQUE)");
				
				db.execSQL(
						"CREATE TABLE "+PERSON_TABLE+" ( "
								+ PERSON_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "
								+PERSON_NAME + " TEXT UNIQUE, "
								+SESSION_ID + " INTEGER, "
								+AMT_CONTRIBUTED + " REAL, "
								+PREVIOUS_AMT + " REAL,"															
								+CURRENT_AMT + " REAL"
								+")");
//								+ " FOREIGN KEY ("+SESSION_ID+") REFERENCES "+"SESSIONS"+" ("+SESSION_ID+"))");
				
				db.execSQL(
						"CREATE TABLE "+TRANSACTION_TABLE+" ( "
								+TRANS_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
								+SESSION_ID  + " INTEGER, "
								+PERSON_NAME + " TEXT , "
								+AMT_SPEND   + " INTEGER, "
								+TRANS_TIME + " INTEGER, "															
								+COMMENT    + " TEXT" 
								+");");
				
				
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
					
		}

		
		//The onUpdade method will only get called if the version number.is updated. If you update your database schema, make sure to update the version number or the database will not get updated to the latest version.
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("PicnicFilter","On Upgrade method");
		Log.d("PicnicFilter", "oldVersion:"+oldVersion+"  newVersion:"+newVersion);
			
			
		}
				
		
	}
	
		
	
	public List<Person> getAllPersons() 
	{
		List<Person> persons=new ArrayList<>();
		Cursor c=null;
		try
		{
			c= database.rawQuery(
					"select * from " + PERSON_TABLE, //This selects all of the rows
					null                           //There are no selection args since you’re selecting all of the records.
					);
			while (c.moveToNext()) {
				int pId=c.getInt(c.getColumnIndex(PERSON_ID));
			    String pName=c.getString(c.getColumnIndex(PERSON_NAME));
			    int sessionId=c.getInt(c.getColumnIndex(SESSION_ID));
			    float amtContributed=c.getFloat(c.getColumnIndex(AMT_CONTRIBUTED));
			    float prevAmt=c.getFloat(c.getColumnIndex(PREVIOUS_AMT));
			    float currAmt=c.getFloat(c.getColumnIndex(CURRENT_AMT));
			    Person p=new Person(pId,sessionId,pName,amtContributed,prevAmt,currAmt);
			    persons.add(p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			c.close();
		}
		return persons;
		
	}
	public List<Transaction> getAllTransactions() 
	{
		Cursor c=null;
		List<Transaction> trans=new ArrayList<>();
		try
		{
			c= database.rawQuery(
					"select * from " + TRANSACTION_TABLE, //This selects all of the rows
					null                           //There are no selection args since you’re selecting all of the records.
					);
			while (c.moveToNext()) {
			    int transId=c.getInt(c.getColumnIndex(TRANS_ID));
			    int sessionId=c.getInt(c.getColumnIndex(SESSION_ID));
			    String personName=c.getString(c.getColumnIndex(PERSON_NAME));
			    float amtSpend=c.getFloat(c.getColumnIndex(AMT_SPEND));
			    Long transTime=c.getLong(c.getColumnIndex(TRANS_TIME));
			    Calendar cal = Calendar.getInstance();
			    cal.setTimeInMillis(transTime);
			    String comments=c.getString(c.getColumnIndex(COMMENT));
			    
			    Transaction t=new Transaction(transId,sessionId,getPersonByName(personName),amtSpend,cal,comments);
			    trans.add(t);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			c.close();
		}
		return trans;
		
	}


	public Person getPersonByName(String personName) {
		List<Person> persons=new ArrayList<>();
		persons=getAllPersons();
		for(Person p:persons)
		{
			if(p.getName().equals(personName))
			{
				return p;
			}
		}
		return null;
	}


	@Override
	public int insertSession(Session session) {
		ContentValues contentValues = new ContentValues();		
		contentValues.put(SESSION_NAME, session.getName());
		int id=(int)database.insert(SESSION_TABLE, null, contentValues);
		Log.d("PicnicFilter","sessionId:"+id);
		return id;
//		String select[]={SESSION_ID};
//		Cursordatabase.query(false, SESSION_TABLE, select, SESSION_NAME +"='"+session.getName()+"' ", null, null, null, null, null);
	}


	@Override
	public void updateSession(Session session) {		
		ContentValues contentValues = new ContentValues();		
		contentValues.put(SESSION_ID, session.getId());
		contentValues.put(SESSION_NAME, session.getName());
		database.update(SESSION_TABLE, contentValues, SESSION_ID+"="+session.getId(), null);
	}


	@Override
	public List<Session> getAllSessions() {
		Cursor c=null;
		List<Session> sessions=new ArrayList<>();
		try
		{
			c= database.rawQuery(
					"select * from " + SESSION_TABLE, //This selects all of the rows
					null                           //There are no selection args since you’re selecting all of the records.
					);
			while (c.moveToNext()) {
			    int sessionId=c.getInt(c.getColumnIndex(SESSION_ID));
			    String sessionName=c.getString(c.getColumnIndex(SESSION_NAME));
			    sessions.add(new Session(sessionId,sessionName));
			    
			}
						
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			c.close();
		}
		return sessions;
	}
	


	@Override
	public Session getSessionById(int sessionId) {
		List<Session> sessions=getAllSessions();
		for(Session s:sessions)
		{
			if(s.getId()==sessionId)
			{
				return s;
			}
		}
		return null;
	}


	@Override
	public void deleteSession(int sessionId) {
		
		database.rawQuery(
				"delete  from " + SESSION_TABLE +" where "+SESSION_ID+" ="+sessionId+"", //This selects all of the rows
				null                           //There are no selection args since you’re selecting all of the records.
				);
	}


	@Override
	public void insertPerson(Person person) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(SESSION_ID, person.getSessionId());
		contentValues.put(PERSON_NAME, person.getName());
		contentValues.put(AMT_CONTRIBUTED, person.getAmtContributed());
		contentValues.put(PREVIOUS_AMT, person.getPreviousAmt());
		contentValues.put(CURRENT_AMT, person.getCurrentAmt());
		database.insert(PERSON_TABLE, null, contentValues);
		
	}


	@Override
	public void updatePerson(Person person) {		
		ContentValues contentValues = new ContentValues();
		contentValues.put(SESSION_ID, person.getSessionId());
		contentValues.put(PERSON_NAME, person.getName());
		contentValues.put(AMT_CONTRIBUTED, person.getAmtContributed());
		contentValues.put(PREVIOUS_AMT, person.getPreviousAmt());
		contentValues.put(CURRENT_AMT, person.getCurrentAmt());
		database.update(PERSON_TABLE,  contentValues,PERSON_ID+"="+person.getId(),null);
	}


	@Override
	public void updatePersonByName(String oldName, String newName) {
		
		
	}


	@Override
	public List<Person> getPersonsBySession(int sessionId) {
		List<Person> persons=new ArrayList<>();
		List<Person> personList=new ArrayList<>();
		persons=getAllPersons();
		for(Person p:persons)
		{
			if(p.getSessionId()==sessionId)
			{
				personList.add(p);
			}
		}
		return personList;
	}


	


	@Override
	public void deletePerson(String personName) {
		database.rawQuery(
				"delete  from " + PERSON_TABLE +" where "+PERSON_NAME+" ='"+personName+"'", //This selects all of the rows
				null                           //There are no selection args since you’re selecting all of the records.
				);
		
	}


	@Override
	public int insertTransaction(Transaction transaction) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(SESSION_ID, transaction.getSessionId());
		contentValues.put(PERSON_NAME, transaction.getPerson().getName());
		contentValues.put(AMT_SPEND, transaction.getAmtSpend());
		contentValues.put(TRANS_TIME, transaction.getTransTime().getTimeInMillis());
		contentValues.put(COMMENT, transaction.getComment());
		return (int)database.insert(TRANSACTION_TABLE, null, contentValues);		
	}


	@Override
	public void updateTransaction(Transaction transaction) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(SESSION_ID, transaction.getSessionId());
		contentValues.put(PERSON_NAME, transaction.getPerson().getName());
		contentValues.put(AMT_SPEND, transaction.getAmtSpend());
		contentValues.put(TRANS_TIME, transaction.getTransTime().getTimeInMillis());
		contentValues.put(COMMENT, transaction.getComment());
		database.update(TRANSACTION_TABLE, contentValues, TRANS_ID+"="+transaction.getId(),null);		
		
	}


	@Override
	public List<Transaction> getTransactionsBySessions(int sessionId) {
		List<Transaction> trns=new ArrayList<>();
		List<Transaction> trnsList=new ArrayList<>();
		trns=getAllTransactions();
		for(Transaction t:trns)
		{
			if(t.getSessionId()==sessionId)
			{
				trnsList.add(t);
			}
		}
		return trnsList;
	}


	@Override
	public Transaction getTransactionById(int transId) {
		List<Transaction> trns=null;
		trns=getAllTransactions();
		for(Transaction t:trns)
		{
			if(t.getId()==transId)
			{
				return t;
			}
		}
		return null;
	}


	@Override
	public void deleteTransaction(int transactionId) {
		database.rawQuery(
				"delete  from " + TRANSACTION_TABLE +" where "+TRANS_ID+" ="+transactionId+"", //This selects all of the rows
				null                           //There are no selection args since you’re selecting all of the records.
				);
		
	}
	@Override
	public Person getPersonById(int personId) {
		
		List<Person> personList=getAllPersons();
		Person person=null;
		for(Person p:personList)
		{
			if(p.getId()==personId)
			{
				person=p;
			}
		}
		return person;
		
	}
}

