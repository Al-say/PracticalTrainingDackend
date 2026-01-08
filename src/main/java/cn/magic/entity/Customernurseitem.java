package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//客户护理设置
@Data
@TableName("customernurseitem")
public class Customernurseitem {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private Integer itemId; // 护理项目编号
    private Integer customerId; // 客户编号
    private Integer levelId; // 护理级别编号
    private Integer nurseNumber; // 购买数量
    private Integer isDeleted; // 逻辑删除标记（0：显示，1：隐藏）
    private Date buyTime; // 服务购买日期
    private Date maturityTime; // 服务到期日
}
