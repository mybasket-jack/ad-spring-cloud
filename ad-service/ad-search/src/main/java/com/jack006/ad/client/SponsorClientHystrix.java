package com.jack006.ad.client;

import com.jack006.ad.client.vo.AdPlan;
import com.jack006.ad.client.vo.AdPlanGetRequest;
import com.jack006.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定义一个 hystrix短路器
 *
 * @Author jack
 * @Since 1.0 2020/2/16 9:52
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-ad-sponsor error");
    }
}
