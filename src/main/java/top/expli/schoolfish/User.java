package top.expli.schoolfish;

/**
 *
 */
public abstract class User {


    String uID;
    String userName;
    String userPassword;
    String profile;
    public String getuID() {
        return uID;
    }
    public void setuID(String uID) {
        this.uID = uID;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
