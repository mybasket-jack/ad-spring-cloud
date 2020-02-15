package com.jack006.ad.controller;

import com.alibaba.fastjson.JSON;
import com.jack006.ad.exception.AdException;
import com.jack006.ad.service.ICreativeService;
import com.jack006.ad.vo.CreativeRequest;
import com.jack006.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创意控制类
 *
 * @Author jack
 * @Since 1.0 2020/2/15 15:52
 */
@RestController
@Slf4j
public class CreateCreativeOPController {

    private final ICreativeService creativeService;

    @Autowired
    public CreateCreativeOPController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException{
        log.info("ad-sponsor: createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
