package cn.magic.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("beddetails")
public class BedDetails {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;  // 主键
    private Integer isDeleted; // 逻辑删除标记 （0：显示；1：隐藏）
    private Date startDate;  //床位起始日期
    private Date endDate;   //床位结束日期
    private String bedDetails; //床位详情信息
    private Integer customerId; //客户ID
    private Integer bedId;  //床位ID
}
