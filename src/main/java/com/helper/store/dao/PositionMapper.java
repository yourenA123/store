package com.helper.store.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author sheamus
 * @date 2019.3.5
 */
@Mapper
@Repository
public interface PositionMapper {
    /**
     * 根据商铺ID获取位置
     *
     * @param param
     * @return
     */
    String getPositionByGymId(Map<String, Object> param);

    /**
     * 获取商铺坐标
     *
     * @return
     */
    List<Map<String, Object>> getPosition();
}