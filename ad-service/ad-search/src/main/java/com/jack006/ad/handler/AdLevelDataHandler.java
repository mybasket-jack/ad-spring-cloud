package com.jack006.ad.handler;

import com.alibaba.fastjson.JSON;
import com.jack006.ad.dump.table.*;
import com.jack006.ad.index.DataTable;
import com.jack006.ad.index.IndexAware;
import com.jack006.ad.index.adplan.AdPlanIndex;
import com.jack006.ad.index.adplan.AdPlanObject;
import com.jack006.ad.index.adunit.AdUnitIndex;
import com.jack006.ad.index.adunit.AdUnitObject;
import com.jack006.ad.index.creative.CreativeIndex;
import com.jack006.ad.index.creative.CreativeObject;
import com.jack006.ad.index.creativeunit.CreativeUnitIndex;
import com.jack006.ad.index.creativeunit.CreativeUnitObject;
import com.jack006.ad.index.district.UnitDistrictIndex;
import com.jack006.ad.index.interest.UnitItIndex;
import com.jack006.ad.index.keyword.UnitKeywordIndex;
import com.jack006.ad.mysql.constant.OpType;
import com.jack006.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 1、索引之间存在着层级的划分，也就是依赖关系的划分
 * 2、加载全量索引其实是增量索引，"添加"的一种特殊实现
 *
 *
 * @Author jack
 * @Since 1.0 2020/2/16 20:23
 */
@Slf4j
public class AdLevelDataHandler {

    public static void handleLevel2(AdPlanTable planTable, OpType type) {
        AdPlanObject planObject = new AdPlanObject(
                planTable.getId(),
                planTable.getUserId(),
                planTable.getPlanStatus(),
                planTable.getStartDate(),
                planTable.getEndDate()
        );
        handleBinlogEvent(
                DataTable.of(AdPlanIndex.class),
                planObject.getPlanId(),
                planObject,
                type
        );
    }

    public static void handleLevel2(AdCreativeTable creativeTable, OpType type) {
        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }

    public static void handleLevel3(AdUnitTable unitTable, OpType type) {
        AdPlanObject adPlanObject = DataTable.of(
                AdPlanIndex.class
        ).get(unitTable.getPlanId());
        if (null == adPlanObject) {
            log.error("handleLevel3 found AdPlanObject error: {}",unitTable.getPlanId());
            return;
        }
        AdUnitObject unitObject = new AdUnitObject(
                unitTable.getUnitId(),
                unitTable.getUnitStatus(),
                unitTable.getPositionType(),
                unitTable.getPlanId(),
                adPlanObject
        );
        handleBinlogEvent(
                DataTable.of(AdUnitIndex.class),
                unitObject.getUnitId(),
                unitObject,
                type
        );

    }

    public static void handleLevel3(AdCreativeUnitTable creativeUnitTable, OpType type) {
        if (type == OpType.UPDATE) {
            log.error("CreativeUnitIndex not support update");
            return;
        }
        AdUnitObject unitObject = DataTable.of(
                AdUnitIndex.class
        ).get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(
                CreativeIndex.class
        ).get(creativeUnitTable.getAdId());

        if (null == unitObject || null == creativeObject) {
            log.error("AdCreativeUnitTable index error: {}",
                    JSON.toJSONString(creativeUnitTable));
            return;
        }
        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
                creativeUnitTable.getUnitId(),
                creativeUnitTable.getAdId()
        );
        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(
                        creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()
                ),
                creativeUnitObject,
                type
        );
    }

    public static void handleLevel4(AdUnitKeywordTable keywordTable,
                                    OpType type) {

        if (type == OpType.UPDATE) {
            log.error("keyword index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(
                AdUnitIndex.class
        ).get(keywordTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitKeywordTable index error: {}",
                    keywordTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(keywordTable.getUnitId())
        );
        handleBinlogEvent(
                DataTable.of(UnitKeywordIndex.class),
                keywordTable.getKeyword(),
                value,
                type
        );
    }

    public static void handleLevel4(AdUnitItTable unitItTable, OpType type) {

        if (type == OpType.UPDATE) {
            log.error("it index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(
                AdUnitIndex.class
        ).get(unitItTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitItTable index error: {}",
                    unitItTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(unitItTable.getUnitId())
        );
        handleBinlogEvent(
                DataTable.of(UnitItIndex.class),
                unitItTable.getItTag(),
                value,
                type
        );
    }


    public static void handleLevel4(AdUnitDistrictTable unitDistrictTable, OpType type) {
        if (type == OpType.UPDATE) {
            log.error("district index can not support update");
            return;
        }
        AdUnitObject unitObject = DataTable.of(
                AdUnitIndex.class
        ).get(unitDistrictTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitDistrictTable index error: {}",
                    unitDistrictTable.getUnitId());
            return;
        }
        String key = CommonUtils.stringConcat(
                unitDistrictTable.getProvince(),
                unitDistrictTable.getCity()
        );
        Set<Long> value = new HashSet<>(Collections.singleton(unitDistrictTable.getUnitId()));
        handleBinlogEvent(
                DataTable.of(UnitDistrictIndex.class),
                key,
                value,
                type
        );
    }

    private static <K,V> void handleBinlogEvent(
            IndexAware<K, V> index,
            K key,
            V value,
            OpType type) {
        switch (type) {
            case ADD:
                index.add(key,value);
                break;
           case UPDATE:
                index.update(key,value);
                break;
            case DELETE:
                index.delete(key,value);
                break;
            default:
                break;

        }
    }
}
