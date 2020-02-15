package com.jack006.ad.dao.unit_condition;

import com.jack006.ad.entity.unit_condition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 关键词推广单元接口
 *
 * @Author jack
 * @Since 1.0 2020/2/14 21:00
 */
public interface AdUnitKeyWordRepository extends JpaRepository<AdUnitKeyword, Long> {
}
