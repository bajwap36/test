package com.example.briskdelivery;

public class User {

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    int uId;
    String upass;
    String email;
    String uname;
    String phone;
    String address;
    //String ZIPCode;

    public String getUpass() {
        return upass;
    }

    public void setUpass(String userId) {
        this.upass = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String fname) {
        this.uname = fname;
    }

//    public String getLname() {
//        return lname;
//    }
//
//    public void setLname(String lname) {
//        this.lname = lname;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public String getZIPCode() {
//        return ZIPCode;
//    }
//
//    public void setZIPCode(String ZIPCode) {
//        this.ZIPCode = ZIPCode;
//    }

//    public String getPassword() {
//        return Password;
//    }
//
//    public void setPassword(String password) {
//        Password = password;
//    }

    public String getFullName(){
        return this.getUname() + " " + this.getUname();
    }

    public User( String uname, String email, String phone, String address, String idpass ,int uid) {
        this.setEmail(email);
        this.setUname(uname);
        this.setPhone(phone);
        this.setAddress(address);
        this.setUpass(idpass);
        this.setuId(uid);
        //this.setZIPCode(ZIPCode);
    }

//    public User(String upasss, String email, String uname,String phone, String address) {
//        this.setUserId(userId);
//        this.setEmail(email);
//        this.setFname(fname);
//        this.setPhone(phone);
//        this.setAddress(address);
//      //  this.setZIPCode(ZIPCode);
//    }

    public User(){

    }

}
