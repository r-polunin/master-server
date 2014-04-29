package messageSystem.datebase;

import database.UserDataSet;
import messageSystem.Address;
import database.DataAccessObject;


public class MsgAddUser extends MsgToDBService{
	final private String login;
	final private String sessionId;
	final private String password;

	public MsgAddUser(Address from, Address to, String sessionId,String nick, String password){
		super(from,to);
		this.login=nick;
		this.sessionId=sessionId;
		this.password=password;
	}

    @Override
	public void exec(DataAccessObject dbService){
		UserDataSet uds=null;
		if (dbService.addUDS(login, password)){
			uds=dbService.getUDS(login, password);
		}
		Address to=getFrom();
		MsgUpdateUser msg=new MsgUpdateUser(dbService.getAddress(),to,sessionId,uds);
		dbService.getMessageSystem().putMsg(to, msg);
	}
}