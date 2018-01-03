package com.hydra.sso.server.common.token;

import com.hydra.sso.server.common.redis.RedisCache;
import com.hydra.sso.server.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 分布式Redis实现令牌管理
 * Created by yahto on 03/01/2018
 */
@Component
public class RedisTokenManager extends TokenManager {

    /**
     * 是否需要扩展token过期时间
     */
    private Set<String> tokenSet = new CopyOnWriteArraySet<String>();

    @Autowired
    private RedisCache<LoginUser> redisCache;

    @Override
    public void verifyExpired() {
        tokenSet.clear();
    }

    @Override
    public void addToken(String token, LoginUser loginUser) {
        redisCache.set(token, loginUser, tokenTimeout * 1000);
    }

    @Override
    public LoginUser validate(String token) {
        LoginUser loginUser = redisCache.get(token);
        if (loginUser != null && !tokenSet.contains(token)) {
            tokenSet.add(token);
            addToken(token, loginUser);
        }
        return loginUser;
    }

    @Override
    public void remove(String token) {
        redisCache.delete(token);
    }
}
