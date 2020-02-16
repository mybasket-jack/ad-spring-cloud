package com.jack006.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 兴趣推广单元导出对象
 *
 * @Author jack
 * @Since 1.0 2020/2/16 19:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItTable {

    private Long unitId;
    private String itTag;
}
