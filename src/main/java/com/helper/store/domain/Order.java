package com.helper.store.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yanghao
 * @create 2019-08-08 15:07
 * @Description:
 */
@Data
@Getter
@Setter
@ToString
public class Order implements Serializable {
    private Integer order_id;
    private Integer gsId;
    private String orderNo;
    private String createTime;
    private Integer status;
    private Integer addressId;
    private Integer buyUserId;
    private Integer sellUserId;
    private String price;
    private String trackingNumber;
}
