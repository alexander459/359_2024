package mainClasses.data;

import mainClasses.enums.UserType;

public class LogInData {
    private String username;
    private String password;
    private UserType user_type;

    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public UserType getUser_type() {return this.user_type;}

    @Override
    public String toString() {
        return "LogInData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_type=" + user_type +
                '}';
    }

}
