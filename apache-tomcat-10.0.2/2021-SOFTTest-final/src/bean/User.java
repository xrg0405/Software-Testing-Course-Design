package bean;

public class User {
    private String uname;
    private String upwd;
    private String email;
    private String unit;
    private String datetime;
    private String realname;
    private String birthday;
    private String phone;
    private int age;

    public User(String uname, String upwd, String email, String unit, String datetime, String realname, String birthday, String phone,int age) {
        this.uname = uname;
        this.upwd = upwd;
        this.email = email;
        this.unit = unit;
        this.datetime = datetime;
        this.realname = realname;
        this.birthday = birthday;
        this.phone = phone;
        this.age = age;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUname() {
        return uname;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age =age;
    }
}
