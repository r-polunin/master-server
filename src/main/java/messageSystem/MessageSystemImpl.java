package messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import frontend.UserDataImpl;


public class MessageSystemImpl implements MessageSystem{

	private Map<Address,ConcurrentLinkedQueue<Msg>> messages=
			new HashMap<Address,ConcurrentLinkedQueue<Msg>>();
	private AddressService addressService = new AddressServiceImpl();

    @Override
	public void addService (Address address,String name){
		messages.put(address,new ConcurrentLinkedQueue<Msg>());
		addressService.addService(address, name);
	}

    @Override
	public Address getAddressByName(String name){
		return addressService.getAddressByName(name);
	}

    @Override
	public void putMsg(Address to,Msg msg){
		(messages.get(to)).add(msg);
	}

    @Override
    public ConcurrentLinkedQueue<Msg> getMsgQueueByName(String name){
        return messages.get(getAddressByName(name));
    }

    @Override
	public void execForAbonent(Abonent abonent){
		ConcurrentLinkedQueue<Msg> messageQueue=messages.get(abonent.getAddress());
		while(!messageQueue.isEmpty()){
			Msg message=messageQueue.poll();
			message.exec(abonent);
		}
	}
}