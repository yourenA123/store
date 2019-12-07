package com.helper.store.shiro;


import com.helper.store.domain.User;
import com.helper.store.service.AuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sheamus
 * @date 2019.3.4
 */
public class PSRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(PSRealm.class);
    @Autowired
    private AuthService authService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权成功");
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Map<String, Object> params = new HashMap<>();
        params.put("account", (String)authenticationToken.getPrincipal());
        List<User> userInfos = authService.getUserByUserName(params);
        if (userInfos.isEmpty()) {
            throw new UnknownAccountException();
        } else if(userInfos.size() > 1) {
            throw new DisabledAccountException();
        } else {
            User user = userInfos.get(0);
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
    }
}
