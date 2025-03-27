package com.odk.ai.basemanager.deal.user;

import com.odk.ai.basedomain.domain.UserDomain;
import com.odk.ai.baseutil.dto.user.UserRegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserRegisterManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
@Slf4j
@Service
public class UserRegisterManager {

    private UserDomain userDomain;

    public String registerUser(UserRegisterDTO userRegisterDTO) {
        return userDomain.registerUser(userRegisterDTO);
    }

    @Autowired
    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
    }
}
