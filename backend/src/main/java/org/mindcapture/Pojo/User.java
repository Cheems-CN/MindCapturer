package org.mindcapture.Pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private Integer status; // 0-正常, 1-禁用
    private String createTime;
    private String updateTime;
}
