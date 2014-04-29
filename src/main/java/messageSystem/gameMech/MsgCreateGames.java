package messageSystem.gameMech;

import java.util.Map;

import database.UserDataSet;

import gameMechanic.GameMechanicImpl;
import messageSystem.Address;
import gameMechanic.GameMechanic;


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

    @Override
    public void exec(GameMechanic gameMechanic){
		Map<String,String> sessionIdToColor=gameMechanic.createGames(users);
		Address to=gameMechanic.getMessageSystem().getAddressByName("WebSocket");
		MsgUpdateColors msg=new MsgUpdateColors(gameMechanic.getAddress(),to,sessionIdToColor);
		gameMechanic.getMessageSystem().putMsg(to, msg);
	}
}