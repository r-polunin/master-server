package messageSystem.Stroke;

import java.util.Map;

import gameClasses.Stroke;

import gameMechanic.GameMechanic;
import messageSystem.Address;
import gameMechanic.GameMechanicImpl;
import messageSystem.gameMech.MsgToGameMechanic;

public class MsgCheckStroke extends MsgToGameMechanic{
	final private int id;
	final Stroke stroke;
	public MsgCheckStroke(Address from, Address to, int idIn, Stroke data){
		super(from,to);
		stroke=data;
		id=idIn;
	}

    @Override
	public void exec(GameMechanic gameMechanic){
		Map<Integer,Stroke> resp=gameMechanic.checkStroke(id,stroke);
		Address to=gameMechanic.getMessageSystem().getAddressByName("WebSocket");
		MsgDoneStroke msg=new MsgDoneStroke(gameMechanic.getAddress(),to,resp);
		gameMechanic.getMessageSystem().putMsg(to, msg);
	}
}