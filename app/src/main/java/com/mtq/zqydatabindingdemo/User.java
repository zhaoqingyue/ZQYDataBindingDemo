package com.mtq.zqydatabindingdemo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by zhaoqy on 2018/7/18.
 */

public class User extends BaseObservable {

    private String firstName;
    private String lastName;
    private boolean isStudent;

    public User(String firstName, String lastName, boolean isStudent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isStudent = isStudent;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        //notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        //notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setStudent(boolean student) {
        isStudent = student;
        //notifyPropertyChanged(BR.student);
    }

    @Bindable
    public boolean isStudent() {
        return isStudent;
    }
}
