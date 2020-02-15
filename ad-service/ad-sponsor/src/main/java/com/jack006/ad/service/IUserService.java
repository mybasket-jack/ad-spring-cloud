package com.jack006.ad.service;

import com.jack006.ad.exception.AdException;
import com.jack006.ad.vo.CreativeUserRequest;
import com.jack006.ad.vo.CreativeUserResponse;

/**
 * 创建对象的接口
 *
 * @Author jack
 * @Since 1.0 2020/2/14 21:06
 */
public interface IUserService {

    CreativeUserResponse createUser(CreativeUserRequest request) throws AdException;
}
