package com.jack006.ad.controller;

import com.alibaba.fastjson.JSON;
import com.jack006.ad.exception.AdException;
import com.jack006.ad.service.IUserService;
import com.jack006.ad.vo.CreativeUserRequest;
import com.jack006.ad.vo.CreativeUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/2/15 15:13
 */
@RestController
@Slf4j
public class UserOPController {

    private final IUserService iUserService;

    @Autowired
    public UserOPController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/create/user")
    public CreativeUserResponse createUser(
            @RequestBody CreativeUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}", JSON.toJSONString(request));
        return iUserService.createUser(request);

    }
}
