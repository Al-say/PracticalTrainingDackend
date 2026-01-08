package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
//护理级别
@Data
@TableName("nurselevel")
public class Nurselevel {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //主键
    private String levelName; //护理级别
    private Integer levelStatus; //级别状态
    private Integer isDeleted; //逻辑删除标记（0：显示，1：隐藏）
}
