package org.dao;

import java.util.List;

import org.model.Session;

public interface SessionDao {
	public int insertSession(Session session);
	public void updateSession(Session session);
	public List<Session> getAllSessions();
	public Session getSessionById(int sessionId);
	public void deleteSession(int sessionId);
}
