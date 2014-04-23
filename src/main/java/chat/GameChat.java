package chat;

import messageSystem.MessageSystem;

public interface GameChat extends Runnable {
	public MessageSystem getMessageSystem();
	public void createChat(String sessionId1, String sessionId2);
    //public abstract void exec(GameChat gameChat);
}
