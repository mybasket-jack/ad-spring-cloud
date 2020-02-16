package com.jack006.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地域推广单元索引对象
 *
 * @Author jack
 * @Since 1.0 2020/2/16 14:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDistrictObject {

    private Long unitId;
    private String province;
    private String city;

    // <String, Set<long>>
    // province-city
}
