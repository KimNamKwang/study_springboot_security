package com.study.study_springboot_security.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.study_springboot_security.daos.SharedDao;

public class MemberWithAuthorityService {
    @Autowired
    SharedDao sharedDao;

    public Object insert(Object dataMap) {
        String sqpMapId = "Memberwithauthority.insertWithUID";
        Object result = sharedDao.insert(sqpMapId, dataMap);
        return result;
    }
}
