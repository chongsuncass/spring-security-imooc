package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/aysn")
public class AysnController {

    private static final Logger logger = LoggerFactory.getLogger(AysnController.class);

    @GetMapping("order")
    public String order() throws Exception {
        logger.info("主线程开始");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程结束");
                return "下单成功";
            }
        };
        logger.info("主线程结束");
        return callable.call();
    }
}
