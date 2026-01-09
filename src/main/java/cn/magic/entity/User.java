package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

//用户表
@Data
@TableName("user") // 对应数据库表名
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer sex; // 1-男 2-女
    private String email;
    private String phoneNumber;
    private Integer roleId; // 1-管理员 2-护工
    private Date createTime;
    private String createBy;

    // @TableLogic // 如果配置了 MyBatis Plus 逻辑删除，加上这个注解
    private Integer isDeleted; // 0-正常 1-删除
   // @TableField(exist = false)
   // private List<Menu> menuList; // 当前用户角色菜单
}
