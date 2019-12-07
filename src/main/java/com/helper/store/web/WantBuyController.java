package com.helper.store.web;

import com.helper.store.domain.JsonMessage;
import com.helper.store.domain.WantBuy;
import com.helper.store.service.WantBuyService;
import com.helper.store.util.Constants;
import com.helper.store.util.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * @author yanghao
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping(value = "/wantBuy")
public class WantBuyController {
    @Autowired
    WantBuyService wantBuyService;

    /**
     * 出售
     */
    @PostMapping("/saveShoe")
    public JsonMessage saveShoe(Integer id){
        return wantBuyService.saveShoe(id);
    }

    /**
     * 获取想要鞋子信息
     * @param request
     * @return
     */
    @GetMapping(value = "/getUserWantBuy")
    public JsonMessage getUserWantBuy(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            List<Map<String,Object>> wantShoeInfo = wantBuyService.getUserWantBuy(param);
            data.put("wantShoeInfo", wantShoeInfo);
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
     * 获取最大最小价格
     * @param request
     * @return
     */
    @GetMapping(value = "/getPrice")
    public JsonMessage getPrice (HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            Map<String,Object> minPrice = wantBuyService.getMinPrice(param);
            data.put("minPrice", minPrice);
            Map<String,Object> maxPrice = wantBuyService.getMaxPrice(param);
            data.put("maxPrice", maxPrice);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
            result.setData(data);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    @PostMapping(value = "/saveWantBuy")
    public JsonMessage saveWantBuy(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            wantBuyService.saveWantBuy(param);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;
    }

    /**
     * 获取鞋子尺码
     * @param request
     * @return
     */
    @GetMapping("/selectSize")
    public JsonMessage selectSize(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            List<Map<String,Object>> selectSize = wantBuyService.selectSize(param);
            data.put("selectSize",selectSize);
            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
            result.setData(data);
        }catch (Exception e){
            result.setResponseCode(Constants.RES_CODE_101);
            result.setErrorMessage(Constants.RES_MESSAGE_101);
        }
        return result;

    }

}

