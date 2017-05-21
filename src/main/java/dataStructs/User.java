package dataStructs;

/**
 * Created by Administrator on 2017-05-21.
 */
public class User {
    private int userID;
    private String userAccount;
    private String userPassword;

    public User() {

    }

    public User(int userID, String userAccount, String userPassword) {
        this.userID = userID;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

