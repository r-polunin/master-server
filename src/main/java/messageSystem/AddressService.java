package messageSystem;

import base.Abonent;
import messageSystem.Address;

public interface AddressService{
	public void addService (Abonent abonent,String name);
	public Address getAddressByName(String name);
}