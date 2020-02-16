package com.jack006.ad.index.creativeunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创意对象与推广单元对象的索引对象
 *
 * @Author jack
 * @Since 1.0 2020/2/16 15:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitObject {

    private Long adId;
    private Long unitId;

    // <adId-unitId>
}
