package com.jack006.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 推广单元导出对象定义
 *
 * @Author jack
 * @Since 1.0 2020/2/16 18:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanTable {

    private Long id;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;
}

