package messageSystem.datebase;

import java.util.List;

import database.UserDataSet;
import messageSystem.Address;
import database.DataAccessObject;
import messageSystem.MsgToDBService;

public class MsgUpdateUsers extends MsgToDBService{
	List<UserDataSet> users;
	public MsgUpdateUsers(Address from, Address to, List<UserDataSet> users){
		super(from, to);
		this.users=users;
	}
	public void exec(DataAccessObject dbService){
		dbService.updateUsers(users);
	}
}
