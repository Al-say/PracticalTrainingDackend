package cn.magic.dto;

import lombok.Data;
//用户DTO
@Data
public class UserDTO {
    private Integer roleId; //系统角色 1-管理员 2-普通用户
    private Integer pageSize; //页码
    private String nickname; //用户真实姓名
}
