package model;


public  class Worker {
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
    public Worker(){}
    public Worker(String id, String name, String passwd){
        this.id = id;
        this.name = name;
        this.password = passwd;
    }
    public Worker(String id, String name, String passwd, String email, String address){
        this.id = id;
        this.name = name;
        this.password = passwd;
        this.email = email;
        this.address = address;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getAddress(){return address;}
    public void setAddress(String addr){this.address = addr;}
}
