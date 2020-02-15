package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创意服务的响应对象
 *
 * @Author jack
 * @Since 1.0 2020/2/15 11:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeResponse {

    private Long id;
    private String creativeName;
}
