package joinLogin;

import joinLogin.User.AgeGroup;

public class User {
    private String id;
    private String password;
    private String name;
    private AgeGroup ageGroup;  

    public User() {
        this.id = "";
        this.password = "";
        this.name = "";
        this.ageGroup = AgeGroup.UNKNOWN; 
    }

    public User(String id, String password, String name, AgeGroup ageGroup) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.ageGroup = ageGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public enum AgeGroup {
        ADULT, TEENAGER, CHILD_SENIOR, UNKNOWN
    }
}
