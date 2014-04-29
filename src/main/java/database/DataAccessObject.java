package database;

import java.util.List;

import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.MessageSystem;

public interface DataAccessObject extends Runnable, Abonent{

	public MessageSystem getMessageSystem();
	public UserDataSet getUDS(final String login,String password);
	public boolean addUDS(final String login,String password);
	public void updateUsers(List<UserDataSet> users);
}