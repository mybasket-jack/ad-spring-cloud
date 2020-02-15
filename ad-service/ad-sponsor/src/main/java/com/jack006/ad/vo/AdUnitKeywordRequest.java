package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 推广单元关键词维度的请求对象
 *
 * @Author jack
 * @Since 1.0 2020/2/15 10:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordRequest {

    private List<UnitKeyword> unitKeywords;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitKeyword{

        private Long unitId;
        private String keyword;
    }
}
