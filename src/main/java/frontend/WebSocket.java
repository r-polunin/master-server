package frontend;

import gameClasses.Snapshot;
import gameClasses.Stroke;
import messageSystem.Address;

import java.util.Map;


public interface WebSocket extends Runnable{
	public void sendStroke(Map<Integer,Stroke> userIdToStroke);
	public void doneSnapshot(int id, Snapshot snapshot);
	public void updateUsersColor(Map<String, String> usersToColors);
    public Address getAddress();
}