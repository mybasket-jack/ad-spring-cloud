package com.jack006.ad.constant;

import lombok.Getter;

/**
 * 创意类型
 *
 * @Author jack
 * @Since 1.0 2020/2/14 20:42
 */
@Getter
public enum  CreativeType {

    IMAGE(1, "图片"),
    VIDEO(2, "视频"),
    TEXT(3, "文本");

    private int type;
    private String desc;

    CreativeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
