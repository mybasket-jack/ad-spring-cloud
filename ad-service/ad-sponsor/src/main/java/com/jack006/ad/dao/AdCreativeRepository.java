package com.jack006.ad.dao;

import com.jack006.ad.entity.AdCreative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创意的Repository
 *
 * @Author jack
 * @Since 1.0 2020/2/14 20:56
 */
public interface AdCreativeRepository extends JpaRepository<AdCreative, Long> {
}
