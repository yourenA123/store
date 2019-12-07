package com.helper.store.web;


import com.helper.store.dao.BalanceMapper;
import com.helper.store.domain.JsonMessage;
import com.helper.store.service.BalanceService;
import com.helper.store.util.Constants;
import com.helper.store.util.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping("/withdrawalrecord")
public class BalanceController {
    @Autowired
    BalanceService balanceService;

    @PostMapping("/getBalanceInfo")
    public JsonMessage getBalanceInfo(HttpServletRequest request){
        JsonMessage result=new JsonMessage();
        Map<String, Object> data=new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            List<Map<String, Object>> balanceInfo = balanceService.getBalanceInfo(param);
            data.put("balanceInfo",balanceInfo);
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
