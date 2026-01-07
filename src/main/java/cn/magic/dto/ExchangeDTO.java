package cn.magic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//床位调换的参数
@Data
@EqualsAndHashCode(callSuper=false)
public class ExchangeDTO {
    private Integer id; //床位详情编号
    private Integer customerId;  //客户编号
    private String buildingNo; //新楼栋号
    private String newRoomNo; //新房间号
    private Integer newBedId;  //新床位编号
    private Integer oldBedId;  //旧床位编号
    private Date endDate;  //床位使用结束时间
}
