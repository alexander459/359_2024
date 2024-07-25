package mainClasses;

public class Admin {
    private String username;
    private String password;

    public String getName(){return this.username;}
    public String getPassword(){return this.password;}

    public void setName(String name){this.username=name;}
    public void setPassword(String password){this.password=password;}

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
