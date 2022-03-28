package cn.sichu.graduatemall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.sichu.graduatemall.service.OmsPortalOrderService;

/**
 * 取消订单消息的处理者
 * <p>
 * 用于从取消订单的消息队列（mall.order.cancel）里接收消息。
 * 
 * @author sichu
 * @date 2022/03/28
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);
    @Autowired
    private OmsPortalOrderService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId) {
        LOGGER.info("receive delay message orderId:{}", orderId);
        portalOrderService.cancelOrder(orderId);
    }
}
