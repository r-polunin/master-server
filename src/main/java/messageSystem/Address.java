package messageSystem;

import java.util.concurrent.atomic.AtomicInteger;

public class Address{
	static private AtomicInteger abonentIdCreator=new AtomicInteger();
	final private int abonentId;

	public Address(){
		this.abonentId=abonentIdCreator.incrementAndGet();
	}

    public Address(Address address){
        this.abonentId=address.abonentId;
    }

	public int hashCode(){
		return abonentId;
	}
}
