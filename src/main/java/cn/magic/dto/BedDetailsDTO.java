package cn.magic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//床位详情 BedDetailsDTO-床位管理查询条件
@Data
@EqualsAndHashCode(callSuper=false)
public class BedDetailsDTO {
    private String customerName;  //客户姓名
    private Integer pageSize; //每页显示的记录数
    private Integer isDeleted; //查询类型 0-生效床位信息 1-失效床位信息(历史记录)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate; //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;  //结束时间
}
