package cn.sichu.graduatemall.service;

import org.springframework.transaction.annotation.Transactional;

import cn.sichu.graduatemall.common.api.CommonResult;
import cn.sichu.graduatemall.dto.OrderParam;

/**
 * 前台订单管理Service
 * 
 * @author sichu
 * @date 2022/03/28
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
