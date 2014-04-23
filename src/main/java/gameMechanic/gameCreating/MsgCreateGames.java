package gameMechanic.gameCreating;

import java.util.Map;

import datebase.UserDataSet;

import messageSystem.Address;
import gameMechanic.GameMechanic;
import messageSystem.MsgToGameMechanic;


public class MsgCreateGames extends MsgToGameMechanic{
	final private Map<String,UserDataSet> users;
    private Address address = new Address();

	public MsgCreateGames(Address from, Address to, Map<String,UserDataSet> data){
		super(from,to);
		users=data;
	}
    public Address getAddress(){
        return address;
    }

	public void exec(GameMechanic gameMechanic){
		Map<String,String> sessionIdToColor=gameMechanic.createGames(users);
		Address to=gameMechanic.getMessageSystem().getAddressByName("WebSocket");
		MsgUpdateColors msg=new MsgUpdateColors(gameMechanic.getAddress(),to,sessionIdToColor);
		gameMechanic.getMessageSystem().putMsg(to, msg);
	}
}