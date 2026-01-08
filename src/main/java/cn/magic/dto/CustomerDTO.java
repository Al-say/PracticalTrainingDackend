package cn.magic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

//客户信息查询条件
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerDTO {
    private String customerName; //客户姓名
    private Integer pageSize; //页码
    private Integer pageNum;  // 当前第几页
    private Integer manType; //老人类型 1-自理老人 2-护理老人 3-无管家
    private Integer userId; //系统健康管家（护工）
}
