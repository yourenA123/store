package com.helper.store.web;

import com.helper.store.domain.JsonMessage;
import com.helper.store.service.ShoeService;
import com.helper.store.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @create 2019-07-02 15:51
 * @Description:
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping(value = "/type")
public class TypeController {
    @Autowired
    ShoeService shoeService;
    @Autowired
    TypeService typeService;
    /**
     * 商品分类
     * @return
     */
    @GetMapping("/selectType")
    public JsonMessage selectType(){
        return typeService.selectType();
    }
}
