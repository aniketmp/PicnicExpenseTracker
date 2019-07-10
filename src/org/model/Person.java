package org.model;

public class Person {
	private int id;
	private int sessionId;
	private String name;
	private float amtContributed;
	private float previousAmt;
	private float currentAmt;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmtContributed() {
		return amtContributed;
	}
	public void setAmtContributed(float amtContributed) {
		this.amtContributed = amtContributed;
	}
	public float getPreviousAmt() {
		return previousAmt;
	}
	public void setPreviousAmt(float previousAmt) {
		this.previousAmt = previousAmt;
	}
	public float getCurrentAmt() {
		return currentAmt;
	}
	public void setCurrentAmt(float currentAmt) {
		this.currentAmt = currentAmt;
	}
	public Person(int sessionId,String name) {
		super();
		this.sessionId=sessionId;
		this.name = name;
	}
	public Person(String name, int amtContributed) {
		super();
		this.name = name;
		this.amtContributed=amtContributed;
	}
	
	
	public Person(String name, float amtContributed, float previousAmt,
			float currentAmt) {
		super();
		this.name = name;
		this.amtContributed = amtContributed;
		this.previousAmt = previousAmt;
		this.currentAmt = currentAmt;
	}
	public Person(int sessionId,String name,float amtContributed,float previousAmt,float currentAmt)
	{
		this.sessionId=sessionId;
		this.name=name;
		this.amtContributed=amtContributed;
		this.previousAmt=previousAmt;
		this.currentAmt=currentAmt;
	}
	public Person(int personId,int sessionId,String name,float amtContributed,float previousAmt,float currentAmt)
	{
		this.id=personId;
		this.sessionId=sessionId;
		this.name=name;
		this.amtContributed=amtContributed;
		this.previousAmt=previousAmt;
		this.currentAmt=currentAmt;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "sessionId:"+sessionId+" name:"+name+"  "+"amtContributed:"+amtContributed+"  "+"previousAmt:"+previousAmt+"  "+"currentAmt:"+currentAmt;
	}
	
	
	
}
