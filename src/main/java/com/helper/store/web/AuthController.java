package com.helper.store.web;

import com.helper.store.domain.JsonMessage;
import com.helper.store.service.AuthService;
import com.helper.store.service.UserService;
import com.helper.store.util.Constants;
import com.helper.store.util.ParamsUtils;
import com.helper.store.util.RedisUtils;
import com.helper.store.util.SmsUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sheamus
 * @date 2019.7.3
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping(value="/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private static final String MOBILE_NUMBER_ILLEGAL = "isv.MOBILE_NUMBER_ILLEGAL";
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;
    /**
     * 发送验证码
     * @param request
     * @return
     */
    @GetMapping(value = "/getSMS")
    public JsonMessage getSMS(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> param = ParamsUtils.getParmas(request);
        Map<String, Object> codeData = new HashMap<String, Object>(16);
        try {
            String phone = param.get("phone").toString();
            int a = authService.countPhone(phone);
            if(authService.countPhone(phone) != 0){
                //用户已存在
                result.setResponseCode(Constants.RES_CODE_907);
                result.setErrorMessage(Constants.RES_MESSAGE_907);
                return result;
            }
            String vCode = SmsUtil.getPhonemsg(phone);
            if(MOBILE_NUMBER_ILLEGAL.equals(vCode)){
                result.setResponseCode(Constants.RES_CODE_604);
                result.setErrorMessage(Constants.RES_MESSAGE_604);
                return result;
            }
            if(vCode.length() != 6){
                //短信服务出现错误
                result.setResponseCode(Constants.RES_CODE_101);
                result.setErrorMessage(Constants.RES_MESSAGE_101);
                return result;
            }
            codeData.put("phone",phone);
            codeData.put("vCode", vCode);
            codeData.put("createTime", System.currentTimeMillis());
            result.setData(codeData);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 注册
     * @param request
     * @return
     */
    @PostMapping(value = "/signIn")
    public JsonMessage signIn(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Subject subject = SecurityUtils.getSubject();
        try {
            Map<String, Object> param = ParamsUtils.getParmas(request);
            String userCode = param.get("phone").toString();
            String password =param.get("password").toString();
            int a = authService.countPhone(userCode);
            if(authService.countPhone(userCode) != 0){
                //用户已存在
                result.setResponseCode(Constants.RES_CODE_907);
                result.setErrorMessage(Constants.RES_MESSAGE_907);
                return result;
            }
            authService.saveUser(param);
            userService.addIntegral(param);
            UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
            token.setRememberMe(true);
            subject.login(token);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 密码登录
     * @param request
     * @return
     */
    @PostMapping(value = "/loginPS")
    public JsonMessage loginPS(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        String userCode = param.get("phone").toString();
        Subject subject = SecurityUtils.getSubject();
        String userPwd = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(userCode,userPwd);
        token.setRememberMe(true);
        try {
            subject.login(token);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        } catch (UnknownAccountException e) {
            result.setResponseCode(Constants.RES_CODE_908);
            result.setErrorMessage(Constants.RES_MESSAGE_908);
        } catch (DisabledAccountException e) {
            result.setResponseCode(Constants.RES_CODE_904);
            result.setErrorMessage(Constants.RES_MESSAGE_904);
        } catch (AuthenticationException e) {
            result.setResponseCode(Constants.RES_CODE_904);
            result.setErrorMessage(Constants.RES_MESSAGE_904);
        } catch (Exception e) {
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }


    /**
     * 获取阿里验证码
     * @param request
     * @return
     */
    @GetMapping("/getAliSMS")
    public JsonMessage getAliSMS(HttpServletRequest request) {
        JsonMessage result = new JsonMessage();
        Map<String, Object> param = ParamsUtils.getParmas(request);
        Map<String, Object> codeData = new HashMap<String, Object>(16);
        try {
            String phone = param.get("phone").toString();
            String vCode = SmsUtil.getPhonemsg(phone);
            if (MOBILE_NUMBER_ILLEGAL.equals(vCode)) {
                result.setResponseCode(Constants.RES_CODE_604);
                result.setErrorMessage(Constants.RES_MESSAGE_604);
                return result;
            }
            if (vCode.length() != 6) {
                //短信服务出现错误
                result.setResponseCode(Constants.RES_CODE_101);
                result.setErrorMessage(Constants.RES_MESSAGE_101);
                return result;
            }
            codeData.put("phone", phone);
            codeData.put("vCode", vCode);
            codeData.put("createTime", System.currentTimeMillis());
            result.setData(codeData);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        } catch (Exception e) {
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }
}