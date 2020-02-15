package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 推广单元兴趣维度的响应对象
 *
 * @Author jack
 * @Since 1.0 2020/2/15 10:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItResponse {

    private List<Long> ids;

}
