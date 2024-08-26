package com.example.starttdd.chapter7.fake;

import com.example.starttdd.chapter7.AutoDebitInfo;
import com.example.starttdd.chapter7.AutoDebitInfoRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {

    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}
