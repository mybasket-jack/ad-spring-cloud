package com.jack006.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * zuul 启动程序
 *
 * @Author jack
 * @Since 1.0 2020/2/12 22:47
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulGatewayApplicaiton {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplicaiton.class,args);
    }
}
