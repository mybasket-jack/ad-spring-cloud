package com.jack006.ad.constant;

import lombok.Getter;

/**
 * 通用状态类
 *
 * @Author jack
 * @Since 1.0 2020/2/14 15:27
 */
@Getter
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效信息");

    private Integer status;
    private String message;

    CommonStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
