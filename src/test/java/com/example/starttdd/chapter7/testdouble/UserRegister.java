package com.example.starttdd.chapter7.testdouble;

import com.example.starttdd.chapter7.testdouble.stub.WeakPasswordChecker;
import com.example.starttdd.chapter7.testdouble.stub.WeakPasswordException;

public class UserRegister {

    private WeakPasswordChecker passwordChecker;

    public UserRegister(WeakPasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
    }
}
