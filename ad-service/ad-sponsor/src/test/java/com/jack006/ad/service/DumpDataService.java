package com.jack006.ad.service;

import com.alibaba.fastjson.JSON;
import com.jack006.ad.Application;
import com.jack006.ad.constant.CommonStatus;
import com.jack006.ad.dao.AdCreativeRepository;
import com.jack006.ad.dao.AdPlanRepository;
import com.jack006.ad.dao.AdUnitRepository;
import com.jack006.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.jack006.ad.dao.unit_condition.AdUnitItRepository;
import com.jack006.ad.dao.unit_condition.AdUnitKeyWordRepository;
import com.jack006.ad.dao.unit_condition.CreativeUnitRepository;
import com.jack006.ad.dump.DConstant;
import com.jack006.ad.dump.table.*;
import com.jack006.ad.entity.AdCreative;
import com.jack006.ad.entity.AdPlan;
import com.jack006.ad.entity.AdUnit;
import com.jack006.ad.entity.unit_condition.AdUnitDistrict;
import com.jack006.ad.entity.unit_condition.AdUnitIt;
import com.jack006.ad.entity.unit_condition.AdUnitKeyword;
import com.jack006.ad.entity.unit_condition.CreativeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库导出实现
 *
 * @Author jack
 * @Since 1.0 2020/2/16 19:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {
    @Autowired
    private AdPlanRepository planRepository;
    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private AdCreativeRepository creativeRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    @Autowired
    private AdUnitKeyWordRepository unitKeyWordRepository;
    @Autowired
    private AdUnitItRepository unitItRepository;
    @Autowired
    private AdUnitDistrictRepository unitDistrictRepository;

    @Test
    public void dumpAdTableData() {
       dumpAdPlanTable(
               String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_PLAN)
       );
       dumpAdUnitTable(
               String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT)
       );
       dumpAdCreativeTable(
               String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE)
       );
       dumpAdCreativeUnitTable(
               String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE_UNIT)
       );
       dumpAdUnitKeywordTable(
               String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_KEYWORD)
       );
       dumpAdUnitItTable(
               String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_IT)
       );
       dumpAdUnitDistrictTable(
               String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_DISTRICT)
       );
    }

    /**
     * 导出推广计划的数据
     *
     * @param fileName
     */
    private void dumpAdPlanTable(String fileName) {
        List<AdPlan> adPlans = planRepository.findAllByPlanStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(adPlans)) {
            return;
        }
        List<AdPlanTable> planTables = new ArrayList<>();
        adPlans.forEach(p -> planTables.add(
                new AdPlanTable(
                        p.getId(),
                        p.getUserId(),
                        p.getPlanStatus(),
                        p.getStartDate(),
                        p.getEndDate()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdPlanTable planTable : planTables) {
                writer.write(JSON.toJSONString(planTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.info("dumpAdPlanTable error ");
            e.printStackTrace();
        }
    }

    /**
     * 导出推广单元的数据
     *
     * @param fileName
     */
    public void dumpAdUnitTable(String fileName) {
        List<AdUnit> adUnits = adUnitRepository.findAllByUnitStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(adUnits)) {
            return;
        }
        List<AdUnitTable> unitTables = new ArrayList<>();
        adUnits.forEach(u -> unitTables.add(
                new AdUnitTable(
                        u.getId(),
                        u.getUnitStatus(),
                        u.getPositionType(),
                        u.getPlanId()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitTable unitTable : unitTables) {
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.info("dumpAdUnitTable error ");
            e.printStackTrace();
        }
    }

    /**
     * 导出创意的数据记录
     *
     * @param fileName
     */
    public void dumpAdCreativeTable(String fileName) {
        List<AdCreative> creatives = creativeRepository.findAll();
        if (CollectionUtils.isEmpty(creatives)) {
            return;
        }
        List<AdCreativeTable> creativeTables = new ArrayList<>();
        creatives.forEach(c -> creativeTables.add(
                new AdCreativeTable(
                        c.getId(),
                        c.getName(),
                        c.getType(),
                        c.getMaterialType(),
                        c.getHeight(),
                        c.getWidth(),
                        c.getAuditStatus(),
                        c.getUrl()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeTable creativeTable : creativeTables) {
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.info("dumpAdCreativeTable error ");
            e.printStackTrace();
        }
    }

    /**
     * 导出创意与推广单元的关联关系表的导出
     * @param fileName
     */
    public void dumpAdCreativeUnitTable(String fileName) {
        List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
        if (CollectionUtils.isEmpty(creativeUnits)) {
            return;
        }
        List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(c -> creativeUnitTables.add(
                new AdCreativeUnitTable(
                        c.getCreativeId(),
                        c.getUnitId()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeUnitTable creativeUnitTable : creativeUnitTables) {
                writer.write(JSON.toJSONString(creativeUnitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.info("dumpAdCreativeUnitTable error ");
            e.printStackTrace();
        }
    }

    /**
     * 导出关键词的推广单元表数据
     * @param fileName
     */
    public void dumpAdUnitKeywordTable(String fileName) {
        List<AdUnitKeyword> unitKeywords = unitKeyWordRepository.findAll();
        if (CollectionUtils.isEmpty(unitKeywords)) {
            return;
        }
        List<AdUnitKeywordTable> unitKeywordTables = new ArrayList<>();
        unitKeywords.forEach(u -> unitKeywordTables.add(
                new AdUnitKeywordTable(
                        u.getUnitId(),
                        u.getKeyword()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitKeywordTable unitKeywordTable : unitKeywordTables) {
                writer.write(JSON.toJSONString(unitKeywordTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.info("dumpAdUnitKeywordTable error");
        }
    }

    /**
     * 兴趣推广单元数据导出
     * @param fileName
     */
    public void dumpAdUnitItTable(String fileName) {
        List<AdUnitIt> unitIts = unitItRepository.findAll();
        if (CollectionUtils.isEmpty(unitIts)) {
            return;
        }
        List<AdUnitItTable> unitItTables = new ArrayList<>();
        unitIts.forEach(u -> unitItTables.add(
                new AdUnitItTable(
                        u.getUnitId(),
                        u.getItTag()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitItTable unitItTable : unitItTables) {
                writer.write(JSON.toJSONString(unitItTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.info("dumpAdUnitItTable error");
        }
    }

    /**
     * 导出地域推广单元数据
     * @param fileName
     */
    public void dumpAdUnitDistrictTable(String fileName) {
        List<AdUnitDistrict> unitDistricts = unitDistrictRepository.findAll();
        if (CollectionUtils.isEmpty(unitDistricts)) {
            return;
        }
        List<AdUnitDistrictTable> unitDistrictTables = new ArrayList<>();
        unitDistricts.forEach(u -> unitDistrictTables.add(
                new AdUnitDistrictTable(
                        u.getUnitId(),
                        u.getProvince(),
                        u.getCity()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitDistrictTable unitDistrictTable : unitDistrictTables) {
                writer.write(JSON.toJSONString(unitDistrictTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.info("dumpAdUnitDistrictTable error");
        }
    }
}
