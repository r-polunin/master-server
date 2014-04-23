package messageSystem;
import frontend.Frontend;


public abstract class MsgToFrontend extends Msg{

	public MsgToFrontend(Address from, Address to){
		super(from,to);
	}

	public void exec(Address abonent){
		if (abonent instanceof Frontend){
			exec((Frontend)abonent);
		}
	}
	public abstract void exec(Frontend frontend);
}