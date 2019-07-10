package org.dao;

import java.util.List;

import org.model.Session;

import android.content.Context;

public class SqlliteSessionDaoImpl implements SessionDao{

	DatabaseHelper databaseHelper=null;
	public SqlliteSessionDaoImpl() {
		databaseHelper=DatabaseHelper.getInstance();
	}
	@Override
	public int insertSession(Session session) {
		return databaseHelper.insertSession(session);
		
	}

	@Override
	public void updateSession(Session session) {
		databaseHelper.updateSession(session);
		
	}

	@Override
	public List<Session> getAllSessions() {
		// TODO Auto-generated method stub
		return databaseHelper.getAllSessions();
	}

	@Override
	public Session getSessionById(int sessionId) {
		// TODO Auto-generated method stub
		return databaseHelper.getSessionById(sessionId);
	}

	@Override
	public void deleteSession(int sessionId) {
		databaseHelper.deleteSession(sessionId);
		
	}

}
