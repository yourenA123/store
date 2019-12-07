package com.helper.store.web;

import com.helper.store.domain.JsonMessage;
import com.helper.store.service.DisCountsService;
import com.helper.store.util.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yanghao
 * @create 2019-08-02 14:59
 * @Description:
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping("/discounts")
public class DisCountsController {

    @Autowired
    DisCountsService disCountsService;

    /**
     * 查询个人的所有优惠券
     * @param request
     * @return
     */
    @GetMapping("/listDiscounts")
    public JsonMessage list(HttpServletRequest request){
        Map<String,Object> param = ParamsUtils.getParmas(request);
        return disCountsService.listDisCounts(param);
    }

    /**
     * 修改优惠券状态
     * @param id
     * @return
     */
    @PostMapping("/updateDiscounts")
    public JsonMessage updateDiscounts(Integer id){
        return disCountsService.updateDisCounts(id);
    }
}
