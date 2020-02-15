package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * AdUnit 服务接口请求对象
 *
 * @Author jack
 * @Since 1.0 2020/2/15 9:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;

    private Integer positionType;
    private Long budget;

    public boolean createBoolean() {
        return null != planId && !StringUtils.isEmpty(unitName)
                && positionType != null &&budget != null;
    }
}
