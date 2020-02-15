package com.jack006.ad.service;

        import com.jack006.ad.exception.AdException;
        import com.jack006.ad.vo.CreativeRequest;
        import com.jack006.ad.vo.CreativeResponse;

/**
 * 创意服务接口
 *
 * @Author jack
 * @Since 1.0 2020/2/15 11:21
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
