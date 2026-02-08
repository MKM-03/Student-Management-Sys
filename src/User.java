public class User {
    private String name;
    private String email;
    private String phoneNum;


    public User(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone_num) {
        this.phoneNum = phone_num;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
