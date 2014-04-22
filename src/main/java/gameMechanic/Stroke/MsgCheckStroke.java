package gameMechanic.Stroke;

import java.util.Map;

import gameClasses.Stroke;

import messageSystem.Address;
import gameMechanic.GameMechanicImpl;
import messageSystem.MsgToGameMechanic;

public class MsgCheckStroke extends MsgToGameMechanic{
	final private int id;
	final Stroke stroke;
	public MsgCheckStroke(Address from, Address to, int idIn, Stroke data){
		super(from,to);
		stroke=data;
		id=idIn;
	}

	public void exec(GameMechanicImpl gameMechanic){
		Map<Integer,Stroke> resp=gameMechanic.checkStroke(id,stroke);
		Address to=gameMechanic.getMessageSystem().getAddressByName("WebSocket");
		MsgDoneStroke msg=new MsgDoneStroke(gameMechanic.getAddress(),to,resp);
		gameMechanic.getMessageSystem().putMsg(to, msg);
	}
}