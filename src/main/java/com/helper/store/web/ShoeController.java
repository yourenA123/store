package com.helper.store.web;

import com.helper.store.dao.ShoeMapper;
import com.helper.store.domain.JsonMessage;
import com.helper.store.service.ShoeService;
import com.helper.store.util.Constants;
import com.helper.store.util.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanghao
 * @create 2019-07-02 15:51
 * @Description:
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping(value = "/shoe")
public class ShoeController {
    @Autowired
    ShoeService shoeService;

    /**
     * 查询所有鞋子
     * @return
     */
    @GetMapping("/selectShoe")
    public JsonMessage selectShoe(){

        return shoeService.selectShoe();
    }
    /**
     * 模糊查询鞋子
     * @param request
     * @return
     */
    @GetMapping("/selectShoeByKeyword")
    public JsonMessage selectShoeByKeyword(HttpServletRequest request){
        JsonMessage result=new JsonMessage();
        Map<String, Object> data=new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            List<Map<String, Object>> shoeList = shoeService.selectShoeByKeyword(param);
            data.put("shoeList",shoeList);
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


    /**
     * 销量排序
     * @return
     */
    @GetMapping("/selectShoeByNum")
    public JsonMessage selectShoeByNum(){
        return shoeService.selectShoeByNum();
    }

    /**
     * 时间排序
     * @return
     */
    @GetMapping("/selectShoeByTime")
    public JsonMessage selectShoeByTime(){
        return shoeService.selectShoeByTime();
    }

    /**
     * 价格排序 高到低
     * @return
     */
    @GetMapping("/selectShoeByPriceDesc")
    public JsonMessage selectShoeByPriceDesc(){
        return shoeService.selectShoeByPriceDesc();
    }

    /**
     * 价格排序 低到高
     * @return
     */
    @GetMapping("/selectShoeByPriceAsc")
    public JsonMessage selectShoeByPriceAsc(){
        return shoeService.selectShoeByPriceAsc();
    }

    /**
     * 遍历品牌
     * @return
     */
    @GetMapping("/selectBrand")
    public JsonMessage selectBrand(){
        return shoeService.selectBrand();
    }

    /**
     * 商品筛选
     * @param type
     * @param priceRange
     * @param brand
     * @param size
     * @return
     */
    @PostMapping("/selectByTypeAndPriceRangeAndBrandAndSize")
    public JsonMessage selectByTypeAndPriceRangeAndBrandAndSize(Integer type,String priceRange,String brand,Double size){
        return shoeService.selectByTypeAndPriceRangeAndBrandAndSize(type,priceRange,brand,size);
    }

    /**
     * 相关推荐
     * @param type
     * @return
     */
    @GetMapping("/recommended")
    public JsonMessage recommended(Integer type){
        return shoeService.recommended(type);
    }

}
