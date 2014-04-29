package messageSystem;


import java.util.HashMap;
import java.util.Map;

import frontend.UserDataImpl;


public class AddressServiceImpl implements AddressService{
	private Map<String,Address> nameToAddress=
			new HashMap<String,Address>();
	private Map<String,Integer> nameToQuantity=
			new HashMap<String,Integer>();
	private Map<String, Integer> nameToLast = 
			new HashMap<String, Integer>();

	public AddressServiceImpl(){
	}

    @Override
	public void addService (Address abonent,String name){
		if(!nameToQuantity.containsKey(name))
			nameToQuantity.put(name, 0);
		nameToQuantity.put(name, nameToQuantity.get(name)+1);
		nameToAddress.put(name+(nameToQuantity.get(name)).toString(), abonent);
		if(nameToQuantity.get(name)==1)
			nameToLast.put(name, 1);
	}

    @Override
	public Address getAddressByName(String name){
		if (!nameToQuantity.containsKey(name)){
			throw new RuntimeException("Service "+name+" not found");
		}
		String number = String.valueOf(nameToLast.get(name));
		nameToLast.put(name, (nameToLast.get(name)+1)%nameToQuantity.get(name)+1);
		return nameToAddress.get(name+number);
	}

}