package com.mtq.zqydatabindingdemo;

/**
 * Created by zhaoqy on 2018/7/18.
 */

public class User {

    private String firstName;
    private String lastName;
    private boolean isStudent;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, boolean isStudent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isStudent = isStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isStudent() {
        return isStudent;
    }
}
