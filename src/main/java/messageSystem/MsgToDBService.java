package messageSystem;

import base.Abonent;
import base.DataAccessObject;


public abstract class MsgToDBService extends Msg{

	public MsgToDBService(Address from, Address to){
		super(from,to);
	}

	public void exec(Abonent abonent){
		if (abonent instanceof DataAccessObject){
			exec((DataAccessObject)abonent);
		}
	}
	public abstract void exec(DataAccessObject dbService);
}