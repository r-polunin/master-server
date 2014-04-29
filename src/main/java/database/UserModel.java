package database;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.sql.Timestamp;

public class UserModel {
    private int id;
    private String nickname;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastVisit;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp registrationDate;

    private int rating;
    private int winQuantity;
    private int loseQuantity;

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Timestamp lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getWinQuantity() {
        return winQuantity;
    }

    public void setWinQuantity(int winQuantity) {
        this.winQuantity = winQuantity;
    }

    public int getLoseQuantity() {
        return loseQuantity;
    }

    public void setLoseQuantity(int loseQuantity) {
        this.loseQuantity = loseQuantity;
    }

    public void incrementLoseQuantity() {
        this.loseQuantity++;
    }

    public void incrementWinQuantity() {
        this.winQuantity++;
    }

    public void updateRating(int diff) {
        this.rating += diff;
    }
}