package netty.userguide.Time;

import java.util.Date;

public class UnixTime {
	private final int value;
	
	public UnixTime() {
		this((int) (System.currentTimeMillis()/1000L + TimeServerHandler.secSince1900));
	}
	
	public UnixTime(int value){
		this.value = value;
	}
	
	public int value(){
		return value;
	}
	
	@Override
	public String toString(){
		return new Date((value() - TimeServerHandler.secSince1900) * 1000L).toString();
	}
}
