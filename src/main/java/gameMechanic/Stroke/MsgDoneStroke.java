package gameMechanic.Stroke;

import java.util.Map;

import gameClasses.Stroke;

import messageSystem.Address;
import frontend.WebSocket;
import messageSystem.MsgToWebSocket;


public class MsgDoneStroke extends MsgToWebSocket{
	final private Map<Integer,Stroke> idToStroke;

	public MsgDoneStroke(Address from, Address to, Map<Integer, Stroke> data){
		super(from,to);
		idToStroke=data;
	}

	public void exec(WebSocket webSocket){
		webSocket.sendStroke(idToStroke);
	}
}