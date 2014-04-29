package messageSystem;

import database.DataAccessObject;


public abstract class MsgToDBService extends Msg{

	public MsgToDBService(Address from, Address to){
		super(from,to);
	}

	public void exec(Address abonent){
		if (abonent instanceof DataAccessObject){
			exec((DataAccessObject)abonent);
		}
	}
	public abstract void exec(DataAccessObject dbService);
}