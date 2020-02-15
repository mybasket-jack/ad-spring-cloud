package com.jack006.ad.service.impl;

import com.jack006.ad.constant.Constants;
import com.jack006.ad.dao.AdCreativeRepository;
import com.jack006.ad.entity.AdCreative;
import com.jack006.ad.exception.AdException;
import com.jack006.ad.service.ICreativeService;
import com.jack006.ad.vo.CreativeRequest;
import com.jack006.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创意服务接口的实现
 *
 * @Author jack
 * @Since 1.0 2020/2/15 11:32
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final AdCreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(AdCreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdCreative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(),creative.getName());
    }
}
