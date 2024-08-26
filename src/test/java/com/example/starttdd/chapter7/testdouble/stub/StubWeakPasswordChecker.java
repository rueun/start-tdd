package com.example.starttdd.chapter7.testdouble.stub;

import com.example.starttdd.chapter7.testdouble.WeakPasswordChecker;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }
}
