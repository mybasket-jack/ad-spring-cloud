package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推广单元服务响应对象
 *
 * @Author jack
 * @Since 1.0 2020/2/15 9:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitResponse {

    private Long id;
    private String unitName;
}
