package com.helper.store.web;

import com.helper.store.domain.JsonMessage;
import com.helper.store.service.GoodsBulkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2019-07-15 14:06
 * @Description:
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping("/goodsBulk")
public class GoodsBulkController {

    @Autowired
    GoodsBulkService goodsBulkService;

    /**
     * 所有尺码
     * @param shoeId
     * @return
     */
    @GetMapping("/selectSize")
    public JsonMessage selectSize(Integer shoeId){
        return goodsBulkService.selectSize(shoeId);
    }
}
