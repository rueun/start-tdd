package com.example.starttdd.chapter7;

public class RegisterResult {
    private CardValidity validity;
    private boolean success;

    private RegisterResult(CardValidity validity, boolean success) {
        this.validity = validity;
        this.success = success;
    }

    public static RegisterResult error(CardValidity validity) {
        return new RegisterResult(validity, false);
    }

    public static RegisterResult success() {
        return new RegisterResult(null, true);
    }

    public CardValidity getValidity() {
        return validity;
    }

    public boolean isSuccess() {
        return success;
    }
}
