package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/2/12 23:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private  T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
