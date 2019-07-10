package org.dao;

import java.util.ArrayList;
import java.util.List;
import org.model.Session;

public class SessionDaoImpl implements SessionDao{
	//String [] sessionsArray={"Trip to allibag!","My banglore trip.. ","Finally UK is not far","Goa me to jana padega","Akkalkot tirtha yartra","My favorite place Gangtok","Daring to go to Haunted House","Zari mari udyan in 5 mins"};
	String [] sessionsArray={};
	List<Session> sessions=new ArrayList<>();
	int sessionId;
	public SessionDaoImpl()
	{
		for(sessionId=0;sessionId<sessionsArray.length;sessionId++)
		{
			sessions.add(new Session(sessionId,sessionsArray[sessionId]));
		}
	}
	@Override
	public int insertSession(Session session) {		
		sessions.add(session);
		throw new RuntimeException("Method not properly implemented");
		
	}

	@Override
	public void updateSession(Session session) {
		for(Session s:sessions)
		{
			if(s.getId()==session.getId())
			{
				sessions.remove(s);
				sessions.add(session);				
				return;
			}
		}
		
	}

	@Override
	public List<Session> getAllSessions() {
		
		return sessions;
	}

	@Override
	public Session getSessionById(int sessionId) 
	{
		Session s=null;
		for(Session s1:sessions)
		{
			if(s1.getId()==sessionId)
			{
				return s=s1;
			}
		}
		return s;
		
	}

	@Override
	public void deleteSession(int sessionId) {
		// TODO Auto-generated method stub
		
		for(int i=0;i< sessions.size(); i++)
		{
			if(sessions.get(i).getId()==sessionId)
			{
				sessions.remove(i);
				return;
			}
		}
		
	}

}
