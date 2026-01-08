package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//护理级别与项目中间表
@Data
@TableName("nurselevelitem")
public class Nurselevelitem {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //主键
    private Integer levelId; //关联护理级别
    private Integer itemId; //关联护理项目
}
