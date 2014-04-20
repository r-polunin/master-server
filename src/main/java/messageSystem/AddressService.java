package messageSystem;

import frontend.UserDataImpl;

public interface AddressService{
	public void addService (Address abonent,String name);
	public Address getAddressByName(String name);
}