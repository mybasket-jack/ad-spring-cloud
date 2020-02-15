package com.jack006.ad.dao;

import com.jack006.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户的Repository
 *
 * @Author jack
 * @Since 1.0 2020/2/14 20:47
 */
public interface AdUserRepository  extends JpaRepository<AdUser, Long> {

    /**
     * 根据用户名查找用户记录
     * @param username
     * @return
     */
    AdUser findByUsername(String username);
}
