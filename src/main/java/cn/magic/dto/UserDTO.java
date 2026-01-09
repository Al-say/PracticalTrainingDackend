package cn.magic.dto;

import lombok.Data;

// 用户DTO，用于接收前端查询参数
@Data
public class UserDTO {
    private Integer roleId;   // 系统角色 1-管理员 2-普通用户
    private Integer pageSize; // 每页显示多少条
    private Integer curPage;  // 当前页码
    private String nickname;  // 用户真实姓名（模糊查询）
}
