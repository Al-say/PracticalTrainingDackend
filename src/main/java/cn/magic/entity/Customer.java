package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

// Customer对象
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("customer")
public class Customer {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private Integer isDeleted; // 逻辑删除标记（0：未删除，1：已删除）
    private String customerName; // 客户姓名
    private Integer customerAge; // 年龄
    private Integer customerSex;   //性别 0：男，1：女
    private String idcard; // 身份证号
    private String roomNo; // 房间号
    private String buildingNo; // 所属楼房
    private Date checkinDate; //入住时间
    private Date expirationDate; //合同到期时间
    private String contactTel; // 联系电话
    private Integer bedId; // 床号
    @TableField("psychosomatic_state")  // 明确指定数据库字段名
    private String psychosomaticStatus; //身心状况
    private String attention; //注意事项
    private Date birthday; //出生日期
    private String height; //身高
    private String weight; //体重
    private String bloodType; //血型
    @TableField("filepath") // 明确指定数据库字段名为filepath
    private String filePath; // 头像路径
    private Integer userId; // 关联系统健康管家(护工)
    private Integer levelId; //护理等级
    private String familyMember; //家属
}
