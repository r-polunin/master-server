package messageSystem.Stroke;

import gameClasses.Snapshot;

import messageSystem.Address;
import frontend.WebSocket;
import messageSystem.gameMech.MsgToWebSocket;


public class MsgDoneSnapshot extends MsgToWebSocket{
	final private Snapshot snapshot;
	final private int id;
	public MsgDoneSnapshot(Address from, Address to, int idIn, Snapshot data){
		super(from,to);
		snapshot=data;
		id=idIn;
	}

	public void exec(WebSocket webSocket){
		webSocket.doneSnapshot(id, snapshot);
	}
}