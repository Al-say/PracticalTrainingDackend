package cn.magic.dto;

import lombok.Data;
//修改密码DTO
@Data
public class UpdatePasswordDTO {
    private Long userId;
    private String password;
    private String oldPassword;
}
