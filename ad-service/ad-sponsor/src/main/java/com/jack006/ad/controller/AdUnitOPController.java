package com.jack006.ad.controller;

import com.alibaba.fastjson.JSON;
import com.jack006.ad.exception.AdException;
import com.jack006.ad.service.IAdUnitService;
import com.jack006.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推广单元控制器
 *
 * @Author jack
 * @Since 1.0 2020/2/15 15:39
 */
@RestController
@Slf4j
public class AdUnitOPController {

    private final IAdUnitService unitService;

    @Autowired
    public AdUnitOPController(IAdUnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping("/create/adUnit")
    public AdUnitResponse createAdUnit(
            @RequestBody AdUnitRequest request) throws AdException{
        log.info("ad-sponsor: createAdPlan -> {}", JSON.toJSONString(request));
        return unitService.createUnit(request);
    }

    @PostMapping("/create/unitKeyword")
    public AdUnitKeywordResponse createAdUnitKeyword(
            @RequestBody AdUnitKeywordRequest request) throws AdException {
        log.info("ad-sponsor: createAdUnitKeyword -> {}",JSON.toJSONString(request));
        return unitService.createUnitKeyword(request);
    }

    @PostMapping("/create/unitIt")
    public AdUnitItResponse createUnitIt(
            @RequestBody AdUnitItRequest request) throws AdException {
        log.info("ad-sponsor: createUnitIt ->",JSON.toJSONString(request));
        return unitService.createUnitIt(request);
    }

    @PostMapping("/create/unitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(
            @RequestBody AdUnitDistrictRequest request) throws AdException {
        log.info("ad-sponsor: createUnitDistrict -> {}", JSON.toJSONString(request));
        return unitService.createUnitDistrict(request);
    }

    @PostMapping("/create/creativeUnit")
    public CreativeUnitResponse createCreativeUnit(
            @RequestBody CreativeUnitRequest request) throws AdException {
        log.info("ad-sponsor: createCreativeUnit -> {}",JSON.toJSONString(request));
        return unitService.createCreativeUnit(request);
    }
}
