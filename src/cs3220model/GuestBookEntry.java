package cs3220model;

import java.util.Date;

public class GuestBookEntry {
	static int count = 0;
	
	private int id;
	private String name;
	private String message;
	private Date created;
	
	public GuestBookEntry() {}
	
	public GuestBookEntry(String name, String message) {
		super();
		this.name = name;
		this.message = message;
		this.created = new Date();
		this.id = count++;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
