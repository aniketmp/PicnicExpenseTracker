package org.model;

public class Session {
	private int id;
	private String name;
	public boolean checked;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Session(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Session( String name) {
		super();	
		this.name = name;
	}
	@Override
	public String toString() {
		return "Session [id=" + id + ", name=" + name + ", checked=" + checked
				+ "]";
	}
	
	

}
