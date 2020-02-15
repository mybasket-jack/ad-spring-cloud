package com.jack006.ad.service;

import com.jack006.ad.entity.AdPlan;
import com.jack006.ad.exception.AdException;
import com.jack006.ad.vo.AdPlanGetRequest;
import com.jack006.ad.vo.AdPlanRequest;
import com.jack006.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * 推广计划的接口
 *
 * @Author jack
 * @Since 1.0 2020/2/15 8:49
 */
public interface IAdPlanService {

    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广计划
     * @param request
     * @return
     * @throws AdException
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws  AdException;

    /**
     * 删除推广计划
     * @param request
     * @throws AdException
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
