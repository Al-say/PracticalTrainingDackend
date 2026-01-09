package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;
//用户表
@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private Date createTime; // 创建时间
    private Integer createBy; // 创建者
    private Date updateTime;    // 更新时间
    private Integer updateBy;   // 更新者
    private Integer isDeleted; // 逻辑删除标记（0：显示，1：隐藏
    private String nickname; // 真实姓名
    private String username; // 系统账号
    private String password; // 密码
    private Integer sex; // 性别
    private String phoneNumber; // 手机号
    private String email; // 邮箱地址
    private Integer roleId; // 系统角色编号
   // @TableField(exist = false)
   // private List<Menu> menuList; // 当前用户角色菜单
}
