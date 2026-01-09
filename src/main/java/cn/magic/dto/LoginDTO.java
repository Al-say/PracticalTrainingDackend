package cn.magic.dto;

import lombok.Data;

//登录参数
@Data
public class LoginDTO {
    private String username;
    private String password;
    private String code;
}
