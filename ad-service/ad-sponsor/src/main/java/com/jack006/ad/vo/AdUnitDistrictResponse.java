package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 推广单元地域维度的响应对象
 *
 * @Author jack
 * @Since 1.0 2020/2/15 10:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictResponse {

    private List<Long> ids;
}
