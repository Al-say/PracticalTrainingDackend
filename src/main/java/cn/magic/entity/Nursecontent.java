package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//护理内容
@Data
@TableName("nursecontent")
public class Nursecontent {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //主键
    private String serialNumber; //编号
    private String nursingName; //名称
    private String servicePrice; // 价格
    private String message; //描述
    private Integer status; //状态 1：启用，2：停用
    private String executionCycle; //执行周期
    private String executionTimes; //执行次数
    private Integer isDeleted; //逻辑删除标记（0：显示；1：隐藏）
}
