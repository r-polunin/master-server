package messageSystem;

import frontend.UserDataImpl;
import messageSystem.Address;
import messageSystem.Msg;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;


public interface MessageSystem{
	public void addService (UserDataImpl abonent,String name);
	public Address getAddressByName(String name);
	public void putMsg(Address to,Msg msg);
	public void execForAbonent(UserDataImpl abonent);
}