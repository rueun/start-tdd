package com.example.starttdd.chapter7.testdouble;

public class UserRegister {

    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository, EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }

        userRepository.findById(id)
            .ifPresent(user -> {
                throw new DupIdException();
            });

        userRepository.save(new User(id, pw, email));
        emailNotifier.sendRegisterEmail(email);
    }
}
