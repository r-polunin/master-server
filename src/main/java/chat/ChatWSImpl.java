package chat;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import datebase.UserDataSet;

import frontend.UserDataImpl;
import org.json.simple.parser.ParseException;

public class ChatWSImpl  extends WebSocketAdapter{
	public ChatWSImpl(){
	}

    private void parseJson(String message, String sessionId, String startServerTime, String text) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(message);
        sessionId=json.get("sessionId").toString();
        startServerTime=json.get("startServerTime").toString();
        text=json.get("text").toString();
    }
    private boolean isChaterValid(String sessionId, String startServerTime) {
        return ((sessionId!=null)&&
                (startServerTime!=null)&&
                UserDataImpl.checkServerTime(startServerTime));
    }

    private boolean isChatTextValid(String sessionId, String startServerTime, String text) {
        return ((text!=null)&&
                (!text.equals(""))&&
                (isChaterValid(sessionId, startServerTime)));
    }

	@Override
	public void onWebSocketText(String message) {
		if (isNotConnected()) {
			return; 
		}
		String sessionId=null,startServerTime=null;
		String text=null;
		try{

            parseJson(message,sessionId,startServerTime,text);
		}
		catch (Exception ignor){
		}
		if(isChatTextValid(sessionId, startServerTime, text)){
			addMessageToChat(sessionId, text);
		}
		else if(isChaterValid(sessionId, startServerTime)){
			addNewChater(sessionId);
		}
	}

	private void addNewChater(String sessionId){
		UserDataImpl.putSessionIdAndChatWS(sessionId, this);
	}
	
	private void addMessageToChat(String sessionId, String text){
		UserDataSet user = UserDataImpl.getLogInUserBySessionId(sessionId);
		if(user!=null){
			GameChatImpl.sendMessage(sessionId, text);
		}
	}
	
	public static void sendMessage(String sessionId, String message){
		try{
			UserDataImpl.getChatWSBySessionId(sessionId).sendString(message);
		}
		catch(Exception ignor){
		}
	}
}