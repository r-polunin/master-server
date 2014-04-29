package gameMechanic;

import gameClasses.Snapshot;
import gameClasses.Stroke;

import java.util.Map;

import database.UserDataSet;
import messageSystem.Address;
import messageSystem.MessageSystem;

public interface GameMechanic extends Runnable{
	public Map<String, String> createGames(Map<String, UserDataSet> users);
	public Map<Integer, Stroke> checkStroke(int id, Stroke stroke);
	public void removeUser(String sessionId);
	public Snapshot getSnapshot(int id);
	public MessageSystem getMessageSystem();
    public Address getAddress();
}