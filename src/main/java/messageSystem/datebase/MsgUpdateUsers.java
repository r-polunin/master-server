package messageSystem.datebase;

import java.util.List;

import database.UserDataSet;
import messageSystem.Address;
import database.DataAccessObject;

public class MsgUpdateUsers extends MsgToDBService{
	List<UserDataSet> users;
	public MsgUpdateUsers(Address from, Address to, List<UserDataSet> users){
		super(from, to);
		this.users=users;
	}

    @Override
	public void exec(DataAccessObject dbService){
		dbService.updateUsers(users);
	}
}
