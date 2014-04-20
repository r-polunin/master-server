package messageSystem;

import frontend.UserDataImpl;

public interface AddressService{
	public void addService (UserDataImpl abonent,String name);
	public Address getAddressByName(String name);
}