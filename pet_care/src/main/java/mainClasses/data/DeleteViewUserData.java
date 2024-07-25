package mainClasses.data;

import mainClasses.enums.UserType;

public class DeleteViewUserData {
    private String username;
    private UserType user_type;

    public String getUsername() {return this.username;}
    public UserType getUser_type() {return this.user_type;}

    public void setUsername(String username) {this.username = username;}
    public void setUser_type(UserType user_type) {this.user_type = user_type;}

    @Override
    public String toString() {
        return "DeleteUserData{" +
                "username='" + username + '\'' +
                ", user_type=" + user_type +
                '}';
    }
}
