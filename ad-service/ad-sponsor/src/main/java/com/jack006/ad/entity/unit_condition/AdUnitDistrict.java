package com.jack006.ad.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 地域推广单元
 *
 * @Author jack
 * @Since 1.0 2020/2/14 20:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit_district")
public class AdUnitDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id",nullable = false)
    private Long unitId;

    @Basic
    @Column(name = "province", nullable = false)
    private String province;

    @Basic
    @Column(name = "city", nullable = false)
    private String city;

    public AdUnitDistrict(long unitId, String province, String city) {
        this.unitId = unitId;
        this.province = province;
        this.city = city;
    }
}
