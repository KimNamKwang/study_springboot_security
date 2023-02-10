package com.study.study_springboot_security.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.study.study_springboot_security.configurations.PrincipalUser;
import com.study.study_springboot_security.daos.SharedDao;

public class PrincipalUserService implements UserDetailsService {
    @Autowired
    SharedDao sharedDao;

    @Override
    /* uri가 login일때 spring security가 호출 */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* query문으로 select 함 */
        String sqlMapId = "Memberwithauthority.selectByUID";
        Object usernameObj = username;
        /* Spring에서는 DB에 갔다오는 부분에서 로그인에 username만 요구하고 password는 요구하지 않는다 */
        Map<String, String> resultMap = (Map<String, String>) sharedDao.getOne(sqlMapId, usernameObj);

        /* session에 등록 */
        PrincipalUser principalUser = new PrincipalUser(resultMap);
        /*
         * 진행과정
         * 생성자가 만들어지고 resultMap을 받는다. PrincipalUser의 메소드들이 생기고
         * 담을 수 있는 상태가 된다. 아래에서 return하게 되면 authentication을 건드린다.
         * authentication이 PrincipalUser의 getPasseord()를 가져와서 password를 체크한다
         */
        return principalUser;
    }

}
