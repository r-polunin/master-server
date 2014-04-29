package database;

import utils.TimeHelper;

import java.sql.Date;
import java.sql.Timestamp;

public class UserDataSet{

	private UserModel mUserModel;
    //widget
	private int postStatus;
	private String color;

	public UserDataSet(UserModel userModel){
        this.mUserModel = userModel;
        visit();
		postStatus=0;
		color=null;
	}



	public UserDataSet(){
		mUserModel = new UserModel();
        visit();
        postStatus=0;
		color=null;
	}

	public void makeLike(UserDataSet uds){
		this.mUserModel=uds.getModel();
		this.postStatus=uds.getPostStatus();
		this.color=uds.getColor();
        visit();
	}

	public String getNick(){
		return mUserModel.getNickname();
	}

	public int getId(){
		return mUserModel.getId();
	}

	public long getLastVisit(){
		return mUserModel.getLastVisit().getTime();
	}

    public int getRating(){
        return mUserModel.getRating();
    }

    public int getWinQuantity(){
        return mUserModel.getWinQuantity();
    }

    public int getLoseQuantity(){
        return mUserModel.getLoseQuantity();
    }

    public void setPostStatus(int quantity){
		postStatus=quantity;
	}

	public int getPostStatus(){
		return postStatus;
	}

	public void setColor(String col){
		color=col;
	}
	
	public String getColor(){
		return color;
	}

    public UserModel getModel() {
        return this.mUserModel;
    }

    public void visit(){
        mUserModel.setLastVisit(new Timestamp(TimeHelper.getCurrentTime()));
    }
	
	public void lose(int diff){
		mUserModel.incrementLoseQuantity();
        mUserModel.updateRating(-diff);
	}
	
	public void win(int diff){
		mUserModel.incrementWinQuantity();
		mUserModel.updateRating(diff);
	}
}