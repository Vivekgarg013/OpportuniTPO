package com.codewizards.opportunitpo;

public class Users {
    String userId,profilePic,name,email,mobile,address,rollNo,branch,semester,xRollNo,xSchool,xPercentage,xiiRollNo,xiiSchool,xiiPercentage,pass;
    Users(){};

    public Users(String userId, String profile, String name, String email, String phone, String address, String rollNO, String branch, String semester, String xRollNo, String xSchool, String xPercentage, String xiiRollNo, String xiiSchool, String xiiPercentage, String pass) {
        this.userId=userId;
        this.profilePic=profile;
        this.name=name;
        this.email=email;
        this.mobile=phone;
        this.address=address;
        this.rollNo=rollNO;
        this.branch=branch;
        this.semester=semester;
        this.xRollNo=xRollNo;
        this.xSchool=xSchool;
        this.xPercentage=xPercentage;
        this.xiiRollNo=xiiRollNo;
        this.xiiSchool=xiiSchool;
        this.xiiPercentage=xiiPercentage;
        this.pass=pass;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getxRollNo() {
        return xRollNo;
    }

    public void setxRollNo(String xRollNo) {
        this.xRollNo = xRollNo;
    }

    public String getxSchool() {
        return xSchool;
    }

    public void setxSchool(String xSchool) {
        this.xSchool = xSchool;
    }

    public String getxPercentage() {
        return xPercentage;
    }

    public void setxPercentage(String xPercentage) {
        this.xPercentage = xPercentage;
    }

    public String getXiiRollNo() {
        return xiiRollNo;
    }

    public void setXiiRollNo(String xiiRollNo) {
        this.xiiRollNo = xiiRollNo;
    }

    public String getXiiSchool() {
        return xiiSchool;
    }

    public void setXiiSchool(String xiiSchool) {
        this.xiiSchool = xiiSchool;
    }

    public String getXiiPercentage() {
        return xiiPercentage;
    }

    public void setXiiPercentage(String xiiPercentage) {
        this.xiiPercentage = xiiPercentage;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
