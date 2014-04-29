package messageSystem.datebase;

import database.DataAccessObject;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.Msg;


public abstract class MsgToDBService extends Msg {

	public MsgToDBService(Address from, Address to){
		super(from,to);
	}

    @Override
	public void exec(Abonent abonent){
		if (abonent instanceof DataAccessObject){
			exec((DataAccessObject)abonent);
		}
	}
	public abstract void exec(DataAccessObject dbService);
}