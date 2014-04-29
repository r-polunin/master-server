package database;

import java.util.List;

import messageSystem.Address;
import messageSystem.MessageSystem;

public interface DataAccessObject extends Runnable{
	public MessageSystem getMessageSystem();
    public Address getAddress();

	public UserDataSet getUDS(final String login,String password);
	public boolean addUDS(final String login,String password);
	public void updateUsers(List<UserDataSet> users);
}