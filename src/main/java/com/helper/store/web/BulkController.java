package com.helper.store.web;

import com.helper.store.domain.JsonMessage;
import com.helper.store.service.AuthService;
import com.helper.store.service.BulkService;
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
import java.util.List;
import java.util.Map;

/**
 * @author sheamus
 * @date 2019.7.3
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping(value="/bulk")
public class BulkController {
    private static final Logger log = LoggerFactory.getLogger(BulkController.class);
    @Autowired
    private BulkService bulkService;

    /**
     * 获取所有鞋（批发）
     * @param request
     * @return
     */
    @GetMapping(value = "/listShoeBulk")
    public JsonMessage listShoeBulk(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        try {
            List<Map<String,Object>> shoesList = bulkService.listShoe();
            data.put("shoesList", shoesList);
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
     * 获取所有鞋（批发）
     * @param request
     * @return
     */
    @GetMapping(value = "/listStore")
    public JsonMessage listStore(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            List<Map<String,Object>> storeList = bulkService.listStore(param);
            data.put("storeList", storeList);
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
     * 获取商店信息
     * @param request
     * @return
     */
    @GetMapping(value = "/getStoreInfoById")
    public JsonMessage getStoreInfoById(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            Map<String,Object> storeInfo = bulkService.getStoreInfoById(param);
            data.put("storeInfo", storeInfo);
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
     * 获取商店信息
     * @param request
     * @return
     */
    @GetMapping(value = "/listgoodsBulk")
    public JsonMessage listgoodsBulk(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            List<Map<String,Object>> goodBulkList = bulkService.listgoodsBulk(param);
            data.put("goodBulkList", goodBulkList);
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
     * 获取商店信息
     * @param request
     * @return
     */
    @PostMapping(value = "/getOrderBulkByNo")
    public JsonMessage getOrderBulkByNo(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            Map<String,Object> orderInfo = bulkService.saveOrder(param);
            data.put("orderInfo", orderInfo);
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
     * 保存订单
     * @param request
     * @return
     */
    @PostMapping(value = "/saveOrder")
    public JsonMessage saveOrder(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            bulkService.updateOrder(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }
}