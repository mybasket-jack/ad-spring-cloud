package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 推官单元关键词维度的服务响应对象
 *
 * @Author jack
 * @Since 1.0 2020/2/15 10:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordResponse {

    private List<Long> ids;
}
