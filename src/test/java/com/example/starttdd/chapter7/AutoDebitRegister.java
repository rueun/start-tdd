package com.example.starttdd.chapter7;

public class AutoDebitRegister {
    private final CardNumberValidator validator;
    private final AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());
        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }

        AutoDebitInfo info = repository.findOne(req.getUserId());
        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            info = new AutoDebitInfo(req.getUserId(), req.getCardNumber());
        }
        repository.save(info);
        return RegisterResult.success();
    }
}
