package org.model;

public class Result {
	public String from;
	public String to;
	public float amt;
	
	public Result(String from ,String to,float amt)
	{
		this.from=from;
		this.to=to;
		this.amt=amt;
	}
	@Override
	public String toString()
	{
		return from+" has to give "+to+" Rs"+amt;
		
	}
}
