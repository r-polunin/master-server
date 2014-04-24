package frontend;

import java.io.IOException;
import java.util.Map;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import messageSystem.Address;
import messageSystem.MessageSystem;

import gameMechanic.Stroke.MsgCheckStroke;

import datebase.UserDataSet;
import gameClasses.Snapshot;
import gameClasses.Stroke;
import utils.TimeHelper;

public class WebSocketImpl  extends WebSocketAdapter implements WebSocket{
	final private Address address;
	private static MessageSystem messageSystem=null;

	public WebSocketImpl(boolean useMS){
		address=new Address();
		if(useMS)
			messageSystem.addService(address,"WebSocket");
	}

	public WebSocketImpl(){
		address=null;
	}

	public static void setMS(MessageSystem msgSystem){
		messageSystem=msgSystem;
	}

	public Address getAddress() {
		return address;
	}

    private boolean isConnectedRightNow(String sessionId, String startServerTime,
                                        int from_x, int from_y, int to_x, int to_y){
        return ((from_x!=-1)&&(from_y!=-1)&&(to_x!=-1)&&(to_y!=-1)&&(sessionId!=null)&&
                (UserDataImpl.checkServerTime(startServerTime)));
    }
	@Override
	public void onWebSocketText(String message) {
		if (isNotConnected()) {
		}
		String sessionId=null,startServerTime=null;
		int from_x=-1, from_y=-1, to_x=-1, to_y=-1;
		String status=null;
		JSONObject json=null;
		try{
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(message);
            sessionId=json.get("sessionId").toString();
            startServerTime=json.get("startServerTime").toString();
            from_x=Integer.parseInt(json.get("from_x").toString());
            from_y=Integer.parseInt(json.get("from_y").toString());
            to_x=Integer.parseInt(json.get("to_x").toString());
            to_y=Integer.parseInt(json.get("to_y").toString());
            status=json.get("status").toString();
		}
		catch (ParseException parseException) {
            sessionId = parseException.getMessage();
		}
		catch (Exception ignor){
            sessionId = ignor.getMessage();
		}
		if(isConnectedRightNow(sessionId, startServerTime, from_x, from_y, to_x, to_y)){
			checkStroke(sessionId, to_x, to_y, from_x, from_y, status);
		}
		else if ((sessionId!=null)&&(UserDataImpl.checkServerTime(startServerTime))){
			addNewWS(sessionId);
		}
	}

	private void addNewWS(String sessionId){
		UserDataSet userSession = UserDataImpl.getLogInUserBySessionId(sessionId);
		if(userSession!=null){
			userSession.visit();
			UserDataImpl.putSessionIdAndWS(sessionId, this);
		}
	}

	private void checkStroke(String sessionId, int to_x, int to_y, int from_x, int from_y, String status){
		Stroke stroke=new Stroke(to_x,to_y,from_x,from_y,status);
		UserDataSet userSession = UserDataImpl.getLogInUserBySessionId(sessionId);
		userSession.visit();
		Address to=messageSystem.getAddressByName("GameMechanic");
		MsgCheckStroke msg=new MsgCheckStroke(address, to, userSession.getId(), stroke);
		messageSystem.putMsg(to, msg);
	}

	public void sendStroke(Map<Integer,Stroke> userIdToStroke){
		String sessionId;
		UserDataSet userSession;
		Stroke stroke;
		try{
			for(Integer userId:userIdToStroke.keySet()){
				sessionId=UserDataImpl.getSessionIdByUserId(userId);
				userSession=UserDataImpl.getLogInUserBySessionId(sessionId);
				userSession.visit();
				stroke=new Stroke(userIdToStroke.get(userId));
				stroke.setColor(userSession.getColor());
				UserDataImpl.getWSBySessionId(sessionId).sendString(stroke.toString());
			}
		}
		catch (Exception e){
			System.err.println("\nError:");
			System.err.println("WebSocketImpl, doneStroke");
			System.err.println(e.getMessage());
		}
	}
    private boolean setColorDependOnUser(String sessionId, String color) throws IOException {
        String black="{\"color\":\"black\"}",white="{\"color\":\"white\"}";
        UserDataSet userSession=UserDataImpl.getLogInUserBySessionId(sessionId);
        if(color==black){
            userSession.setColor("b");
            UserDataImpl.getWSBySessionId(sessionId).sendString(black);
            return true;
        }
        else if(color==white){
            userSession.setColor("w");
            UserDataImpl.getWSBySessionId(sessionId).sendString(white);
            return true;
        }
        return false;
    }

	public boolean updateUsersColor(Map<String, String> usersToColors) {
        String color;
		for(String sessionId:usersToColors.keySet()){
			try{
				color=usersToColors.get(sessionId);
                return setColorDependOnUser(sessionId, color);
			}
			catch(Exception e){
				System.err.println("\nError:");
				System.err.println("WebSocketImpl, updateUsersColor");
				System.err.println(e.getMessage());
			}
		}
        return false;
	}

	public void doneSnapshot(int userId, Snapshot snapshot) {
		String sessionId = UserDataImpl.getSessionIdByUserId(userId);
		try{
			UserDataImpl.getWSBySessionId(sessionId).sendString(snapshot.toString());
		}
		catch(Exception e){
			System.err.println("\nError:");
			System.err.println("WebSocketImpl, doneSnapshot");
			System.err.println(e.getMessage());
		}
	}

	public void run() {
		while(true){
			messageSystem.execForAbonent(address);
			TimeHelper.sleep(200);
		}

	}
}