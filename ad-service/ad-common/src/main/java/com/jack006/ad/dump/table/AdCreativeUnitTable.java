package com.jack006.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创意与推广单元的导出字段
 *
 * @Author jack
 * @Since 1.0 2020/2/16 19:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeUnitTable {

    private Long adId;
    private Long unitId;
}
