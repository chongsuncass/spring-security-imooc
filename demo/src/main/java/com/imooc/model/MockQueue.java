package com.imooc.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockQueue.class);

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    /**
     * 模拟下单
     * @param placeOrder
     */
    public void setPlaceOrder(String placeOrder) {
        LOGGER.info("接到下单请求:" + placeOrder);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completeOrder = placeOrder;
        }).start();
        LOGGER.info("下单请求处理完毕");
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
