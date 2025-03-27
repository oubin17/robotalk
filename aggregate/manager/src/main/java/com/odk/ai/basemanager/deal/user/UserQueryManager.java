package com.odk.ai.basemanager.deal.user;

import com.odk.ai.basedomain.domain.UserQueryDomain;
import com.odk.ai.baseutil.entity.UserEntity;
import com.odk.ai.basedomain.dataobject.user.UserAccessTokenDO;
import com.odk.ai.basedomain.repository.user.UserAccessTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserQueryManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
@Slf4j
@Service
public class UserQueryManager {

    private UserAccessTokenRepository accessTokenRepository;

    private UserQueryDomain userQueryDomain;

    /**
     * 根据userId查找用户
     *
     * @param userId
     * @return
     */
    public UserEntity queryByUserId(String userId) {
        return this.userQueryDomain.queryByUserId(userId);
    }

    /**
     * 根据登录凭证查找用户
     *
     * @param tokenType
     * @param tokenValue
     * @return
     */
    public UserEntity queryByAccessToken(String tokenType, String tokenValue) {

        UserAccessTokenDO userAccessTokenDO = accessTokenRepository.findByTokenTypeAndTokenValue(tokenType, tokenValue);
        if (null == userAccessTokenDO) {
            return null;
        }
        return queryByUserId(userAccessTokenDO.getUserId());
    }

    @Autowired
    public void setAccessTokenRepository(UserAccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Autowired
    public void setUserQueryDomain(UserQueryDomain userQueryDomain) {
        this.userQueryDomain = userQueryDomain;
    }
}
