package com.jack006.ad.service;

import com.jack006.ad.exception.AdException;
import com.jack006.ad.vo.*;

/**
 * 推广单元的服务接口
 *
 * @Author jack
 * @Since 1.0 2020/2/15 9:47
 */
public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request)
            throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request)
            throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request)
            throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)
            throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request)
            throws AdException;
}
