package messageSystem;

import base.Abonent;
import messageSystem.Address;
import messageSystem.Msg;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;


public interface MessageSystem{
	public void addService (Abonent abonent,String name);
	public Address getAddressByName(String name);
	public void putMsg(Address to,Msg msg);
	public void execForAbonent(Abonent abonent);
}