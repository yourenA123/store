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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sheamus
 * @date 2019.7.3
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping(value="/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    /**
     * 获取商店信息
     * @param request
     * @return
     */
    @GetMapping(value = "/getUserInfoById")
    public JsonMessage getUserInfoById(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            Map<String,Object> userInfo = userService.getUserInfoById(param);
            data.put("userInfo", userInfo);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
            result.setData(data);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 修改商铺名字
     * @param request
     * @return
     */
    @GetMapping(value = "/updateStoreName")
    public JsonMessage updateStoreName(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            userService.updateStoreName(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 修改常驻地
     * @param request
     * @return
     */
    @GetMapping(value = "/updateAddress")
    public JsonMessage updateAddress(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            userService.updateAddress(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 修改图片
     * @param request
     * @return
     */
    @GetMapping(value = "/updateImg")
    public JsonMessage updateImg(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            userService.updateImg(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public JsonMessage getUserInfo(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        try {
            Map<String,Object> userInfo = userService.getUserInfo(data);
            data.put("userInfo", userInfo);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
            result.setData(data);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     *绑定用户支付宝
     * @param request
     * @return
     */
    @PostMapping("/updateUserAliPay")
    public JsonMessage updateUserAliPay(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            userService.updateUserAliPay(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 用户充值
     * @param request
     * @return
     */
    @PostMapping("/charge")
    public JsonMessage charge(HttpServletRequest request) {
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {

            userService.charge(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        } catch (Exception e) {
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }


    /**
     * 用户提现
     * @param request
     * @return
     */
    @PostMapping("/withdrawal")
    public JsonMessage withdrawal(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {

            userService.saveWithdrawalrecord(param);
            userService.withdrawal(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }
    /**
     *提现记录
     */
    @PostMapping("/saveWithdrawalrecord")
    public JsonMessage saveWithdrawalrecord(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {

            userService.saveWithdrawalrecord(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
    }
        return result;

    }


    /**
     * 增加用户邀请积分
     * @param request
     */
    @PostMapping("/addIntegral")
    public JsonMessage addIntegral(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            userService.addIntegral(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 查找用户邀请码
     * @param request
     * @return
     */
    @PostMapping(value = "/selectInvitationCode")
    public JsonMessage selectInvitationCode(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            Map<String,Object> invitationCode = userService.selectInvitationCode(param);
            data.put("invitationCode", invitationCode);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
            result.setData(data);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 积分换红包
     * @param request
     * @return
     */
    @PostMapping("/redPacket")
    public JsonMessage redPacket(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            Map<String,Object> integral = userService.selectIntegral(data);
            data.put("integral", integral);
            Map<String, Object> money = userService.redPacket(data);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
            result.setData(data);
        }catch (Exception e){
            result.setData(data);
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }




}
