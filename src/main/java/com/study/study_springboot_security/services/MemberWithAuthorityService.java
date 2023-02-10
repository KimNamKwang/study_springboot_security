package com.study.study_springboot_security.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.study_springboot_security.daos.SharedDao;
import com.study.study_springboot_security.utils.CommonUtils;

@Service
public class MemberWithAuthorityService {
    @Autowired
    SharedDao sharedDao;
    @Autowired
    CommonUtils commonUtils;
    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;

    public Object insert(Object dataMap) {
        String sqpMapId = "Memberwithauthority.insertWithUID";
        ((Map) dataMap).put("USERS_UID", commonUtils.getUniqueSequence());
        ((Map) dataMap).put("role", "ROLE_USER");
        /* normal to crypto password */
        String password = (String) ((Map) dataMap).get("password");
        ((Map) dataMap).put("password", bcryptPasswordEncoder.encode(password));
        /* 위의 bcryptPasswordEncoder.encode() 부분은 spring에 있는 기본 암호화 기능을 사용한 것. */
        Object result = sharedDao.insert(sqpMapId, dataMap);
        return result;
    }
}
