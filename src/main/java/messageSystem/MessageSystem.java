package messageSystem;

import frontend.UserDataImpl;
import messageSystem.Address;
import messageSystem.Msg;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;


public interface MessageSystem{
	public void addService (Address abonent,String name);
	public Address getAddressByName(String name);
	public void putMsg(Address to,Msg msg);
	public void execForAbonent(Abonent abonent);
    public ConcurrentLinkedQueue<Msg> getMsgQueueByName(String gameMechanic);
}