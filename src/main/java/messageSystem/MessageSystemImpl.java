package messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import frontend.UserDataImpl;


public class MessageSystemImpl implements MessageSystem{

	private Map<Address,ConcurrentLinkedQueue<Msg>> messages=
			new HashMap<Address,ConcurrentLinkedQueue<Msg>>();
	private AddressService addressService = new AddressServiceImpl();

	public void addService (UserDataImpl abonent,String name){
		messages.put(abonent.getAddress(),new ConcurrentLinkedQueue<Msg>());
		addressService.addService(abonent, name);
	}

	public Address getAddressByName(String name){
		return addressService.getAddressByName(name);
	}

	public void putMsg(Address to,Msg msg){
		(messages.get(to)).add(msg);
	}

	public void execForAbonent(UserDataImpl abonent){
		ConcurrentLinkedQueue<Msg> messageQueue=messages.get(abonent.getAddress());
		while(!messageQueue.isEmpty()){
			Msg message=messageQueue.poll();
			message.exec(abonent);
		}
	}
}