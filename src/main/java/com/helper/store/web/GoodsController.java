package com.helper.store.web;

import cn.hutool.core.text.escape.Html4Escape;
import com.helper.store.domain.JsonMessage;
import com.helper.store.service.GoodsService;
import com.helper.store.util.Constants;
import com.helper.store.util.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanghao
 * @create 2019-07-15 14:06
 * @Description:
 */
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 查找鞋子
     * @param shoeId
     * @return
     */
    @GetMapping("/selectSize")
    public JsonMessage selectSize(Integer shoeId){
        return goodsService.selectSize(shoeId);
    }

    /**
     *
     * @param shoeId
     * @return
     */
    @GetMapping("/selectShoeByShoeId")
    public JsonMessage selectShoeByShoeId(Integer shoeId){
        return goodsService.selectShoeByShoeId(shoeId);
    }

    /**
     *
     * @param shoeId
     * @return
     */
    @GetMapping("/selectOrderByGoodsId")
    public JsonMessage selectOrderByGoodsId(Integer shoeId){
        return goodsService.selectOrderByGoodsId(shoeId);
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/shoesOnSale")
    public JsonMessage shoesOnSale(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            List<Map<String,Object>> saleShoesInfo = goodsService.shoesOnSale(param);
            data.put("saleShoesInfo", saleShoesInfo);
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
     * 上架鞋子
     * @param request
     * @return
     */
    @PostMapping(value = "/sellingShoes")
    public JsonMessage sellingShoes(HttpServletRequest request){
        JsonMessage result = new JsonMessage();
        Map<String, Object> data = new HashMap<String, Object>(16);
        Map<String, Object> param = ParamsUtils.getParmas(request);
        try {
            goodsService.sellingShoes(param);

            result.setResponseCode(Constants.RES_CODE_0);
            result.setErrorMessage(Constants.RES_MESSAGE_0);
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
            Map<String,Object> minPrice = goodsService.getMinPrice(param);
            data.put("minPrice", minPrice);
            Map<String,Object> maxPrice = goodsService.getMaxPrice(param);
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
}
