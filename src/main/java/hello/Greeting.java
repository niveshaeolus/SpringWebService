package hello;

public class Greeting {
	private long id;
	private String message;
	private String dummy;
	
	public Greeting(long id,String msg) {
		this.id = id;
		this.message = msg;
	}
	
	public Greeting(long id,String msg,String dummy) {
		this.id = id;
		this.message = msg;
		this.dummy = dummy;
	}
	
	public long getId() {
		return id;
	}
	
	public String getMsg() {
		return message;
	}
	
	public String getDummy() {
		return dummy;
	}
}
