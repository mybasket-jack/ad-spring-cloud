package com.jack006.ad.client;

/**
 * Feign调用微服务接口
 *
 * @Author jack
 * @Since 1.0 2020/2/16 9:45
 */

import com.jack006.ad.client.vo.AdPlan;
import com.jack006.ad.client.vo.AdPlanGetRequest;
import com.jack006.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "eureka-client-ad-sponsor",fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping(value = "/ad=sponsor/get/adPlan")
    CommonResponse<List<AdPlan>> getAdPlans(
            @RequestBody AdPlanGetRequest request);
}
