package frontend;

import gameClasses.Snapshot;
import gameClasses.Stroke;
import messageSystem.Abonent;
import messageSystem.Address;

import java.util.Map;


public interface WebSocket extends Runnable, Abonent {
	public void sendStroke(Map<Integer,Stroke> userIdToStroke);
	public void doneSnapshot(int id, Snapshot snapshot);
	public boolean updateUsersColor(Map<String, String> usersToColors);
}