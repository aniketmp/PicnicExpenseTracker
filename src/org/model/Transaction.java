package org.model;

import java.util.Calendar;

public class Transaction {
	private int id;
	private int sessionId;
	private Person person;
	private float amtSpend;
	private Calendar transTime;
	private String comment;
	


	
	public Transaction(int sessionId, Person person, float amtSpend,
			Calendar transTime, String comment) {
		super();
		this.sessionId = sessionId;
		this.person = person;
		this.amtSpend = amtSpend;
		this.transTime = transTime;
		this.comment = comment;
	}
	
	public Transaction() {
		super();
	}

	public Transaction(int id, int sessionId, Person person, float amtSpend,
			Calendar transTime, String comment) {
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.person = person;
		this.amtSpend = amtSpend;
		this.transTime = transTime;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public float getAmtSpend() {
		return amtSpend;
	}
	public void setAmtSpend(float amtSpend) {
		this.amtSpend = amtSpend;
	}
	public Calendar getTransTime() {
		return transTime;
	}
	public void setTransTime(Calendar transTime) {
		this.transTime = transTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+id+"  "+"sessionId:"+sessionId+"  "+"person:"+person.getName()+"  "+"amtSpend:"+amtSpend+"  "+"transTime:"+transTime+"  "+"comment:"+comment;
	}
	
	
}
