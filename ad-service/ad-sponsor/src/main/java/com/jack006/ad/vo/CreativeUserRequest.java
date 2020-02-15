package com.jack006.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 *  创建对象的请求对象
 *
 * @Author jack
 * @Since 1.0 2020/2/14 21:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUserRequest {

    private String username;

    public boolean validate() {
        return StringUtils.isEmpty(username);
    }
}
