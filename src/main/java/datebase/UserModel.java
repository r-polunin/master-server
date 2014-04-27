package datebase;

public class UserModel {
    private int id;
    private String nickname;
    private String password;
    private long lastVisit;
    private long registrationDate;
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

    public long getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(long lastVisit) {
        this.lastVisit = lastVisit;
    }

    public long getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(long registrationDate) {
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
}