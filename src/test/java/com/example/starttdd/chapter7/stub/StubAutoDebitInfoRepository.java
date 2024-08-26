package com.example.starttdd.chapter7.stub;

import com.example.starttdd.chapter7.AutoDebitInfo;
import com.example.starttdd.chapter7.AutoDebitInfoRepository;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {
    private AutoDebitInfo saved;

    @Override
    public void save(AutoDebitInfo info) {
        this.saved = info;
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }

    public AutoDebitInfo saved() {
        return saved;
    }
}
