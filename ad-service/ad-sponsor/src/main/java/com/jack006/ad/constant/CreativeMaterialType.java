package com.jack006.ad.constant;

import lombok.Getter;

/**
 * 物料类型
 *
 * @Author jack
 * @Since 1.0 2020/2/14 20:44
 */
@Getter
public enum  CreativeMaterialType {

    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
