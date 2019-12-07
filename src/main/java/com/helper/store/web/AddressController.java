package com.helper.store.web;

import com.helper.store.domain.Address;
import com.helper.store.domain.JsonMessage;
import com.helper.store.service.AddressService;
import com.helper.store.util.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yanghao
 * @create 2019-08-02 15:02
 * @Description:
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * 查询所有收货地址
     * @param request
     * @return
     */
    @GetMapping("/listAddress")
    public JsonMessage listAddress(HttpServletRequest request){
        Map<String, Object> param = ParamsUtils.getParmas(request);
        return addressService.listAddress(param);
    }

    /**
     * 增加收货地址
     * @param request
     * @return
     */
    @PostMapping("/insertAddress")
    public JsonMessage insertAddress(HttpServletRequest request){
        Map<String, Object> param = ParamsUtils.getParmas(request);
        return addressService.insertAddress(param);
    }

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    public JsonMessage deleteAddress(Integer id){
        return addressService.deleteAddress(id);
    }

    /**
     * 编辑收货地址
     * @param address
     * @return
     */
    @PostMapping("/updateAddress")
    public JsonMessage updateAddress(Address address){
        return addressService.updateAddress(address);
    }

}
