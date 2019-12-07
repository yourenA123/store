package com.helper.store.service;

import com.helper.store.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author sheamus
 * @date 2019.7.3
 */
public interface AuthService {
    /**
     * 验证手机号是否存在
     * @param phone
     */
    int countPhone(String phone);

    /**
     * 注册
     * @param param
     */
    void saveUser(Map<String,Object> param);

    /**
     * 根据用户名获取密码
     * @param param
     * @return
     */
    List<User> getUserByUserName(Map<String, Object> param);

}
