package com.jack006.ad.service.impl;

import com.jack006.ad.constant.Constants;
import com.jack006.ad.dao.AdUserRepository;
import com.jack006.ad.entity.AdUser;
import com.jack006.ad.exception.AdException;
import com.jack006.ad.service.IUserService;
import com.jack006.ad.utils.CommonUtils;
import com.jack006.ad.vo.CreativeUserRequest;
import com.jack006.ad.vo.CreativeUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户接口实现
 *
 * @Author jack
 * @Since 1.0 2020/2/14 21:14
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public CreativeUserResponse createUser(CreativeUserRequest request) throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        // 校验同名用户
        AdUser oldUser = userRepository.findByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        // 创建用户
        AdUser newUser = userRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())
        ));
        return new CreativeUserResponse(newUser.getId(), newUser.getUsername(),
                newUser.getToken(), newUser.getCreateTime(), newUser.getUpdateTime()
        );
    }
}
